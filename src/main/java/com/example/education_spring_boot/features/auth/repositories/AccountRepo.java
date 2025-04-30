package com.example.education_spring_boot.features.auth.repositories;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
