package com.jiwon.springsecurityjwt.security;

import com.jiwon.springsecurityjwt.exception.IsNotACorrectAuthorizationInfoException;
import com.jiwon.springsecurityjwt.exception.NoSuchAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityControllerAdvice {
    private static final Logger log = LoggerFactory.getLogger(SecurityControllerAdvice.class);

    @ExceptionHandler(NoSuchAccountException.class)
    public void hasNoAccount(){
        log.debug("There is no such account");
    }

    @ExceptionHandler(IsNotACorrectAuthorizationInfoException.class)
    public void isNotACorrectInfo(){
        log.debug("인증정보가 정확하지 않습니다.");
    }

}
