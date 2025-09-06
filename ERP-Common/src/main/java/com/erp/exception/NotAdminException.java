package com.erp.exception;

import com.erp.context.BaseContext;

/**
 * only admin has access
 */
public class NotAdminException extends BaseException {
    public NotAdminException() {}
    public NotAdminException(String msg) {
        super(msg);
    }
}
