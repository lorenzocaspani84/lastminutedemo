package com.demo.lastminute.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ModelAndView handleCustomException(CustomException ex) {

        ModelAndView model = new ModelAndView("error/io-error");
        model.addObject("httpStatus", ex.getHttpStatus());
        model.addObject("errorMessage", ex.getErrorMessage());

        return model;

    }
}