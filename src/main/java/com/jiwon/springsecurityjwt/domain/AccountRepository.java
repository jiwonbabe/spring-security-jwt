package com.jiwon.springsecurityjwt.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<Account> findByUserId(String userId);
    Optional<Account> findBySocialId(String socialId);
}
