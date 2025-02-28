package com.example.education_spring_boot.repository;

import com.example.education_spring_boot.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
