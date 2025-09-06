package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * email verification sending dto
 */
@Data
public class SendEmailCodeDTO implements Serializable {
    private String email;
}
