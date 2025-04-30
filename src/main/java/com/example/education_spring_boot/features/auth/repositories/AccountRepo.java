package com.example.education_spring_boot.features.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.education_spring_boot.features.auth.models.entities.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
  Account findByUsername(String username);
}
