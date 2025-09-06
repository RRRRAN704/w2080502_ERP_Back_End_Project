package com.erp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * reset password info
 */
@Data
public class ResetPasswordDTO implements Serializable {

    private String email;
    private String verifyCode;
    private String newPassword;
    private String confirmPassword;
}
