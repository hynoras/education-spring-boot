package com.example.education_spring_boot.features.auth.models.dtos;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.education_spring_boot.features.auth.models.entities.Account;

public class CustomUserDetails implements UserDetails {
  private final String username;
  private final String password;
  private final List<GrantedAuthority> authorities;

  public CustomUserDetails(Account account) {
    this.username = account.getUsername();
    this.password = account.getPassword();
    this.authorities = List.of(new SimpleGrantedAuthority(String.valueOf(account.getRole())));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
