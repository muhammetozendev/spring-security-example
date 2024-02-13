package com.muhammetozendev.springsecurityexample.securityconfig;

import com.muhammetozendev.springsecurityexample.securityconfig.jwt.JwtAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {
  private final JwtAuthenticationProvider jwtAuthenticationProvider;

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(List.of(jwtAuthenticationProvider));
  }
}
