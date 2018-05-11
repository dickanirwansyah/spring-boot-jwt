package com.spring.jwt.springbootjwtreact.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReadyUsersException extends RuntimeException {

    public ReadyUsersException(String message){
        super(message);
    }

    public ReadyUsersException(String message , Throwable cause){
        super(message, cause);
    }
}
