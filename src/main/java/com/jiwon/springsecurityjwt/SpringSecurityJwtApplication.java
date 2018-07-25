package com.jiwon.springsecurityjwt;

import com.jiwon.springsecurityjwt.domain.Account;
import com.jiwon.springsecurityjwt.domain.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrapTestAccount(AccountRepository repository, PasswordEncoder passwordEncoder){
        return args -> {
            Account account = new Account();
            account.setPassword(passwordEncoder.encode("1234"));

            repository.save(account);
        };
    }
}
