package com.erp.exception;

/*
* check verfication code for accout recovery
* */

public class CodesDoNotMatchException extends BaseException {

  public CodesDoNotMatchException() {}

  public CodesDoNotMatchException(String msg) {
    super(msg);
  }
}
