package com.jiwon.springsecurityjwt.security.providers;

import com.jiwon.springsecurityjwt.domain.Account;
import com.jiwon.springsecurityjwt.domain.AccountRepository;
import com.jiwon.springsecurityjwt.exception.IsNotACorrectAuthorizationInfoException;
import com.jiwon.springsecurityjwt.exception.NoSuchAccountException;
import com.jiwon.springsecurityjwt.security.AccountContext;
import com.jiwon.springsecurityjwt.security.AccountContextService;
import com.jiwon.springsecurityjwt.security.tokens.PostAuthorizationToken;
import com.jiwon.springsecurityjwt.security.tokens.PreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 인증 객체를 생성하는 메소드.
        // 파라미터로 들어오는 인증객체가 preAuthorizationtoken 이라는 것을 전제.
        PreAuthorizationToken token = (PreAuthorizationToken)authentication;

        String username = token.getUsername();
        String password = token.getPassword();

        Account account = accountRepository.findByUserId(username).orElseThrow(() -> new NoSuchAccountException("정보에 맞는 계정이 없습니다."));

        if(isCorrectPassword(password, account)){
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }

        throw new IsNotACorrectAuthorizationInfoException("인증정보가 정확하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        // 어떤 인증 객체를 담당할지 명시해 주어야 한다.
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, Account account){
        return passwordEncoder.matches(password, account.getPassword());
    }
}
