package com.erp.exception;


/**
 * user not logeed in exception
 */
public class UserNotLoginException extends BaseException {
    public UserNotLoginException() {}
    public UserNotLoginException(String msg) {
        super(msg);
    }
}
