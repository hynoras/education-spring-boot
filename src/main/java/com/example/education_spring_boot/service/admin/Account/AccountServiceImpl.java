package com.example.education_spring_boot.service.admin.Account;

import com.example.education_spring_boot.config.ModelMapperConfig;
import com.example.education_spring_boot.dto.AccountDTO;
import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.repository.admin.AccountRepo;
import org.modelmapper.ModelMapper;
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
    public Account register(AccountDTO accountDTO) {
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
        account.setEmail(accountDTO.getEmail());
        account.setRole(accountDTO.getRole());
        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        accountRepo.save(account);
        return account;
    }
}
