package com.jiwon.springsecurityjwt.exception;

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String msg){
        super(msg);
    }
}
