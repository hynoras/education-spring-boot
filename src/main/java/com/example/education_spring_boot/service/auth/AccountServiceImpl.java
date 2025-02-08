package com.example.education_spring_boot.service.auth;

import com.example.education_spring_boot.dto.account.RegisterRequest;
import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.repository.admin.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
//    private final ModelMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepo accountRepo, PasswordEncoder passwordEncoder/*, ModelMapper accountMapper*/) {
        this.accountRepo = accountRepo;
        this.passwordEncoder = passwordEncoder;
        //this.accountMapper = accountMapper;
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setEmail(registerRequest.getEmail());
        account.setRole(registerRequest.getRole());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        accountRepo.save(account);
        return "Successfully created account!";
    }
}
