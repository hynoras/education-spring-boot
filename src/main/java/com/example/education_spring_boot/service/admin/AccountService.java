package com.example.education_spring_boot.service.admin;

import com.example.education_spring_boot.model.Account;
import com.example.education_spring_boot.repository.admin.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepo accountRepo;

    public Account register(Account account) {
        return accountRepo.save(account);
    }
}
