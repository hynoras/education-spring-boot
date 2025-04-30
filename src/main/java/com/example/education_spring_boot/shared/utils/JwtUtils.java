package com.example.education_spring_boot.shared.utils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
  public static final String SECRET_KEY =
      "64d7193cdc922ea71206fda7866dff151deefa56f7611a7e04aface82fadb9e289dde5ce0269473a2baae377204f27a6dc9df3ad16e7b9d8c087e474cb893aeda0869da5710aaeefd7b7e9abc83b2291d57e47919d095f9c6a87d612f875c75a81558b38061aab4b3fc04a0d4e49588090aef7f6099555a4bf06bddbc30c91d33dc629970f6f231d4657f8622caa40a010f09e89cb476e2290d8a64692772fa9b760bce07b83d14335f3b3afeae2e3410d7d321c7126282d518065563604611907f7fd3c4e3213efdf53cc3e6171ab8cbb06d2898d7e22a1c5552a5d501159ccbe23f9e89335073eefd69f3392e3722dd6a205c289477137aec66dd2026181b7";
  private static final long EXPIRATION_TIME = 1000 * 60 * 60;

  private Key getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(String username) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, username);
  }

  public String createToken(Map<String, Object> claims, String username) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(getSignKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
  }

  public Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    try {
      final String username = extractUsername(token);
      boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
      return isValid;
    } catch (Exception e) {
      return false;
    }
  }
}
