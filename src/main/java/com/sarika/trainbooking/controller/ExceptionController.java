package com.sarika.trainbooking.controller;

import com.sarika.trainbooking.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler({MethodArgumentNotValidException.class, Exception.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<ErrorResponse> defaultExceptionHandler(Exception ex) {
    return new ResponseEntity<>(
        new ErrorResponse("unable to process", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
  }
}
