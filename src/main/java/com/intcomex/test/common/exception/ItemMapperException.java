package com.intcomex.test.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ItemMapperException extends RuntimeException{
private static final long serialVersionUID = 1L;

    private HttpStatus errorCode;
    private String errorMessage;
}
