package com.example.education_spring_boot.service.auth;

import com.example.education_spring_boot.dto.account.CustomUserDetails;
import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.repository.admin.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        if(account == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomUserDetails(account);
    }
}
