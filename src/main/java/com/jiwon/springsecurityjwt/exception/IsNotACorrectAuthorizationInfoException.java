package com.jiwon.springsecurityjwt.exception;

import org.springframework.security.core.AuthenticationException;

public class IsNotACorrectAuthorizationInfoException extends AuthenticationException {

    public IsNotACorrectAuthorizationInfoException(String msg) {
        super(msg);
    }
}
