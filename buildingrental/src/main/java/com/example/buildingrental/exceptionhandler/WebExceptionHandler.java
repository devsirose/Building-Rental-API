package com.example.buildingrental.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(MyException.class)
    public final ResponseEntity<MyException> handleAllExceptions() {
        ResponseEntity<MyException> errResponse =  new ResponseEntity<MyException>(new MyException("My error"), HttpStatus.INTERNAL_SERVER_ERROR);
        return errResponse;
    }
}