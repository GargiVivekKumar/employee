package com.example.employeedemo.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    private final HttpStatus httpStatus;

    private final String reason;

    private final int errorCode;

    public CustomException(HttpStatus httpStatus,String reason, int errorCode){
        super(reason);
        this.httpStatus=httpStatus;
        this.reason=reason;
        this.errorCode=errorCode;
    }
}
