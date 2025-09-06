package com.erp.service.impl;

import com.erp.service.EmailService;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${erp.sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${erp.sendgrid.from-email}")
    private String fromEmail;

    private static final String EMAIL_CODE_PREFIX = "email:reset:";
    private static final int CODE_EXPIRE_TIME = 5; // 5分钟

    @Override
    public boolean sendResetPasswordCode(String email) {
        try {
            // generate codes
            String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));

            // create sendGrid object
            Email from = new Email(fromEmail);
            String subject = "Password Reset Code";
            Email to = new Email(email);

            // create email content
            String htmlContent = buildEmailContent(code);
            Content content = new Content("text/html", htmlContent);

            Mail mail = new Mail(from, subject, to, content);

            // create send grid client object and send email
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();

            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());

                Response response = sg.api(request);


                if (response.getStatusCode() == 202) {
                    // store code and set expiry time
                    String key = EMAIL_CODE_PREFIX + email;
                    redisTemplate.opsForValue().set(key, code, CODE_EXPIRE_TIME, TimeUnit.MINUTES);

                    log.info("SendGrid email send success, email: {}, code: {}, status code: {}",
                            email, code, response.getStatusCode());
                    return true;
                } else {
                    log.error("SendGrid email send failed, email: {}, status code: {}, response body: {}",
                            email, response.getStatusCode(), response.getBody());
                    return false;
                }

            } catch (IOException ex) {
                log.error("SendGrid API call exception, email: {}, error message: {}", email, ex.getMessage());
                return false;
            }

        } catch (Exception e) {
            log.error("email verficarion code failed sending, email: {}, error message: {}", email, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean verifyEmailCode(String email, String code) {
        try {
            String key = EMAIL_CODE_PREFIX + email;
            String savedCode = redisTemplate.opsForValue().get(key);

            if (savedCode != null && savedCode.equals(code)) {
                // delete code after verficiation code has been sent
                redisTemplate.delete(key);
                log.info("Code verified successfully, email: {}", email);
                return true;
            }

            log.warn("Code verification failed, email: {}, code: {}, saved code: {}",
                    email, code, savedCode);
            return false;

        } catch (Exception e) {
            log.error("exception occurred, email: {}, error message: {}", email, e.getMessage());
            return false;
        }
    }

    /**
     * construct http contents
     * @param code verification cpde
     * @return HTML content
     */
    private String buildEmailContent(String code) {
        StringBuilder content = new StringBuilder();
        content.append("<html><body>");
        content.append("<div style='padding: 20px; font-family: Arial, sans-serif;'>");
        content.append("<h2 style='color: #333;'>Password Reset Verification Code</h2>");
        content.append("<p>Hello,</p>");
        content.append("<p>Your verification code is:</p>");
        content.append("<div style='background-color: #f0f0f0; padding: 20px; margin: 20px 0; text-align: center;'>");
        content.append("<span style='font-size: 32px; font-weight: bold; color: #007bff; letter-spacing: 5px;'>")
                .append(code).append("</span>");
        content.append("</div>");
        content.append("<p>The verification code is valid for 5 minutes.</p>");
        content.append("<p>If you didn't request this code, please ignore this email.</p>");
        content.append("</div>");
        content.append("</body></html>");
        return content.toString();
    }
}