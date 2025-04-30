package com.example.education_spring_boot.features.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.education_spring_boot.features.auth.models.dtos.CustomUserDetails;
import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.auth.repositories.AccountRepo;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService {
  private final AccountRepo accountRepo;

  @Autowired
  public CustomUserDetailService(AccountRepo accountRepo) {
    this.accountRepo = accountRepo;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = accountRepo.findByUsername(username);
    if (account == null) {
      throw new UsernameNotFoundException("User Not Found");
    }
    return new CustomUserDetails(account);
  }
}
