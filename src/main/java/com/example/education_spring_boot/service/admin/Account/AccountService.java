package com.example.education_spring_boot.service.admin.Account;

import com.example.education_spring_boot.dto.AccountDTO;
import com.example.education_spring_boot.model.Account;

public interface AccountService {
    public Account register(AccountDTO accountDTO);
}
