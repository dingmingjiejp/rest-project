package com.trizic.restapi.Exception;

import java.util.Date;

import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Exception Handler Class
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handling all exception and return a ResponseEntity
   * @param ex
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Handling AdvisorNotFoundException and return a ResponseEntity
   * @param ex
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(AdvisorNotFoundException.class)
  public final ResponseEntity<Object> handlerAdvisorNotFoundException(AdvisorNotFoundException ex,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
  }


  /**
   * Handling AdvisorNotFoundException and return a ResponseEntity
   * @param ex
   * @param request
   * @return ResponseEntity
   */
  @ExceptionHandler(AllocationPercentageTotalInvalidException.class)
  public final ResponseEntity<Object> handlerAllocationPercentageTotalInvalidException(AllocationPercentageTotalInvalidException ex,
      WebRequest request) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }


  /**
   * Handling MethodArgumentNotValidException and return a ResponseEntity
   * @param ex
   * @param headers
   * @param status
   * @param request
   * @return ResponseEntity
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    String detailMsg = ex.getBindingResult().getAllErrors().stream()
        .map(objectError -> objectError.getDefaultMessage())
        .collect(
            Collectors.toList()).toString();

    ExceptionResponse exceptionResponse = new ExceptionResponse(detailMsg);
    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
}