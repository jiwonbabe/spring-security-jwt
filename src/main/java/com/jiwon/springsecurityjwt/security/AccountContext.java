package com.jiwon.springsecurityjwt.security;

import com.jiwon.springsecurityjwt.domain.Account;
import com.jiwon.springsecurityjwt.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountContext extends User {


    private AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AccountContext fromAccountModel(Account account){
        return new AccountContext(account.getUsername(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole userRole){
        return Arrays.asList(userRole).stream().map(r -> new SimpleGrantedAuthority(userRole.getRoleName())).collect(Collectors.toList());
    }
}
