package com.example.employeedemo.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Errors {
    NOT_FOUND(HttpStatus.NOT_FOUND,"Employee not available", 404);
    private final HttpStatus httpStatus;
    private final String defaultMessage;
    private final int errorCode;

     Errors(HttpStatus httpStatus, String defaultMessage, int errorCode){
        this.httpStatus=httpStatus;
        this.defaultMessage=defaultMessage;
        this.errorCode=errorCode;
    }

    public CustomException asException(){
        return new CustomException(this.httpStatus,this.defaultMessage,this.errorCode);
    }
}
