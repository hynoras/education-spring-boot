package com.example.education_spring_boot.features.auth.utils;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.education_spring_boot.features.auth.models.entities.Account;
import com.example.education_spring_boot.features.auth.repositories.AccountRepo;
import com.example.education_spring_boot.shared.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

@Component
public class AccountDeserializer extends StdDeserializer<Account> {
  private final AccountRepo accountRepo;

  public AccountDeserializer(AccountRepo accountRepo) {
    super(Account.class);
    this.accountRepo = accountRepo;
  }

  @Override
  public Account deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException, JacksonException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    int accountId = ((IntNode) node).intValue();
    Optional<Account> account = accountRepo.findById((long) accountId);
    if (account.isEmpty()) {
      throw new ResourceNotFoundException("Account with id " + accountId + " can not be found!");
    }
    return account.get();
  }
}
