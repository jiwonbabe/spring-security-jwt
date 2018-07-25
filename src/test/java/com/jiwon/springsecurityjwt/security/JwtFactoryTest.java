package com.jiwon.springsecurityjwt.security;

import com.jiwon.springsecurityjwt.domain.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtFactoryTest {
    
    private static final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);

    @Autowired
    private JwtFactory jwtFactory;

    private AccountContext context;

    @Before
    public void setUp() throws Exception {
        Account account = new Account();
        this.context = AccountContext.fromAccountModel(account);
    }

    @Test
    public void TEST_JWT_GENERATE() {
        log.error(jwtFactory.generateToken(this.context));
    }
}