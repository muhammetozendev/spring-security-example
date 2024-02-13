package com.muhammetozendev.springsecurityexample.securityconfig.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
  private Object principal;
  private String token;

  public JwtAuthenticationToken(String token) {
    super(null);
    this.token = token;
  }

  public JwtAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    super.setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }
}
