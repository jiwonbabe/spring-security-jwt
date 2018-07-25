package com.jiwon.springsecurityjwt.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiwon.springsecurityjwt.dtos.TokenDto;
import com.jiwon.springsecurityjwt.security.AccountContext;
import com.jiwon.springsecurityjwt.security.JwtFactory;
import com.jiwon.springsecurityjwt.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtFactory factory;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
        // provider 에서 넘어온 post 토큰을 가지고 filter의 successAUth 메소드 호출
        // posttoken 에서 넘어온 인증결과값에 accountcontext 를 빼내서
        PostAuthorizationToken token = (PostAuthorizationToken) auth;
        AccountContext context = (AccountContext) token.getPrincipal();

        String tokenString = factory.generateToken(context);
        // 만들어진 토큰값을 dto 에 기록.
        processResponse(res, writeDto(tokenString));
    }

    private TokenDto writeDto(String token){
        return new TokenDto(token);
    }

    private void processResponse(HttpServletResponse res, TokenDto tokenDto) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(mapper.writeValueAsString(tokenDto));
    }
}
