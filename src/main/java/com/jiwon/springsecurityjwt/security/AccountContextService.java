package com.jiwon.springsecurityjwt.security;

import com.jiwon.springsecurityjwt.domain.Account;
import com.jiwon.springsecurityjwt.domain.AccountRepository;
import com.jiwon.springsecurityjwt.exception.NoSuchAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AccountContextService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchAccountException("There is no such account"));
        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account){
        return AccountContext.fromAccountModel(account);
    }
}
