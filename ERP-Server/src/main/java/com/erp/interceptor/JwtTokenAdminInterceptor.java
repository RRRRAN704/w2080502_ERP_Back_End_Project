package com.erp.interceptor;

import com.erp.constant.JwtClaimsConstant;
import com.erp.context.BaseContext;
import com.erp.properties.JwtProperties;
import com.erp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT token verification interceptor
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * check jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("current thread id is " + Thread.currentThread().getId());


        //to whether the current method a controller method
        if (!(handler instanceof HandlerMethod)) {
            //if this method is not a dynamic method then allow it to pass
            return true;
        }

        //exclude add order api
        if ("/employee/order".equals(request.getRequestURI()) && "POST".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        //1、get token
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、check jwt
        try {
            log.info("jwt check:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            String position = claims.get(JwtClaimsConstant.POSITION).toString();
            Long departmentId = Long.valueOf(claims.get(JwtClaimsConstant.DEPARTMENT_ID).toString());
            log.info("current enployee id：{}", empId);
            log.info("current enployee position:{}", position);
            log.info("current enployee departmentId:{}", departmentId);
            BaseContext.setCurrentId(empId);
            BaseContext.setCurrentRole(position);
            BaseContext.setCurrentDepartment(departmentId);
            //3、pass
            return true;
        } catch (Exception ex) {
            //4、not pass，send 401 code
            response.setStatus(401);
            return false;
        }
    }
}
