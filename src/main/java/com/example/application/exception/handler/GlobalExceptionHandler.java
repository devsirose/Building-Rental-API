package com.example.application.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<?> handlerSQLException(SQLException e, WebRequest request) {
        String bodyOfResponse = "Error processing database";
        return handleExceptionInternal(e, bodyOfResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<?> handlerConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Error processing the request!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
                HttpStatus.CONFLICT, request);
    }


//    @ExceptionHandler(value = NoResourceFoundException.class)
//    protected ResponseEntity<Object> handlerNoResource(NoResourceFoundException ex, WebRequest request) {
//        String bodyOfResponse = "Resource not found";
//        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(),
//                HttpStatus.NOT_FOUND, request);
//    }
}
