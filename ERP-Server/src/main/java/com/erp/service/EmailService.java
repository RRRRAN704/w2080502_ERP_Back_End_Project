package com.erp.service;


import com.erp.dto.ClientDTO;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.result.PageResult;

import java.util.List;

public interface EmailService {

    /**
     * send verification code
     * @param email
     * @return
     */
    boolean sendResetPasswordCode(String email);

    /**
     * check verfification code match
     * @param email
     * @param code
     * @return
     */
    boolean verifyEmailCode(String email, String code);
}

