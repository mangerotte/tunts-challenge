package com.luizmangerotte.tuntschallenge.error.handler;


import com.luizmangerotte.tuntschallenge.error.StandardError;
import com.luizmangerotte.tuntschallenge.error.exceptions.GeneralSecurityException;
import com.luizmangerotte.tuntschallenge.error.exceptions.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardError> handlerNullPointerException(NullPointerException e,
                                                                     HttpServletRequest request) {
        String error = "Check that the sheet contains all the values filled in correctly";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()), status);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StandardError> handlerIOException(IOException e,
                                                           HttpServletRequest request) {
        String error = "There was an error with the io.java class";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()), status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handlerIllegalArgumentException(IllegalArgumentException e,
                                                            HttpServletRequest request) {
        String error = "Invalid argument";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()), status);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<StandardError> handlerIndexOutOfBoundsException
            (IllegalArgumentException e,
             HttpServletRequest request) {
        String error = "Check that the sheet contains all the values filled in correctly";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()), status);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> handlerRuntimeException(RuntimeException e,
                                                                HttpServletRequest request){
        String error = "An error has occurred";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()), status);
    }
    @ExceptionHandler(GeneralSecurityException.class)
    public ResponseEntity<StandardError> handlerGeneralSecurityException
            (GeneralSecurityException e,
             HttpServletRequest request) {
        String error = "General Security Exception";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()), status);
    }


}
