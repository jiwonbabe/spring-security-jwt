package com.jiwon.springsecurityjwt.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jiwon.springsecurityjwt.security.AccountContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class JwtFactory {
    // 유저의 권한 정보를 담고 있는 토큰을 생성해주는 역할.
    private static String signingKey = "jwttest";
    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    public String generateToken(AccountContext context){
        String token = null;

        try{
            token = JWT.create()
                    .withIssuer("jiwon")
                    .withClaim("USERNAME", context.getAccount().getUserId())
                    .withClaim("USER_ROLE", context.getAccount().getUserRole().getRoleName())
                    .sign(getAlgorithm());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        }
        return token;
    }

    private Algorithm getAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }

}
