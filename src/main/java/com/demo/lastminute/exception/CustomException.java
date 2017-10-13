package com.demo.lastminute.exception;

import org.springframework.http.HttpStatus;

import java.io.IOException;

public class CustomException extends Exception {

    private String errorMessage;

    private HttpStatus httpStatus;

    public CustomException(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
