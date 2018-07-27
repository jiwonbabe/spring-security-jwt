package com.jiwon.springsecurityjwt.security.providers;

import com.jiwon.springsecurityjwt.security.AccountContext;
import com.jiwon.springsecurityjwt.security.jwt.JwtDecoder;
import com.jiwon.springsecurityjwt.security.tokens.JwtPreProcessingToken;
import com.jiwon.springsecurityjwt.security.tokens.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtDecoder jwtDecoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 클라이언트가 맞는 토큰을 보내왔는지 인증을 진행.
        String token = (String) authentication.getPrincipal();
        AccountContext context = jwtDecoder.decodeJwt(token);

        return PostAuthorizationToken.getTokenFromAccountContext(context);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtPreProcessingToken.class.isAssignableFrom(aClass);
    }
}
