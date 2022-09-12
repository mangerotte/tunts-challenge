package com.luizmangerotte.tuntschallenge.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GeneralSecurityException extends Exception {

    public GeneralSecurityException(String message) {
        super(message);
    }
}
