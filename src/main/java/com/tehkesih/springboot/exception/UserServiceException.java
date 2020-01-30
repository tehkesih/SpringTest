package com.tehkesih.springboot.exception;

public class UserServiceException extends RuntimeException {



    public UserServiceException(String message){
        super(message);
    }
}
