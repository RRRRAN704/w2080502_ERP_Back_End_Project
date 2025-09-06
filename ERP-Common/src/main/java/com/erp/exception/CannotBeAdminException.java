package com.erp.exception;

/**
 * admin has no access
 */
public class CannotBeAdminException extends BaseException {
    public CannotBeAdminException() {}
    public CannotBeAdminException(String msg) {
        super(msg);
    }
}
