package com.intcomex.test.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.intcomex.test.common.exception.ItemNotFoundException;

@ControllerAdvice
public class ErrorRestControllerAdvice {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<String> handleEmptyInput(ItemNotFoundException emptyInputException){
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }

}
