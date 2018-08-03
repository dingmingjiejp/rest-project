package com.trizic.restapi.Exception;

import java.util.Date;

/**
 * This class represent the response when exception happened
 */
public class ExceptionResponse {

  private String errorCode;

  public ExceptionResponse(String errorCode) {
    super();
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

}