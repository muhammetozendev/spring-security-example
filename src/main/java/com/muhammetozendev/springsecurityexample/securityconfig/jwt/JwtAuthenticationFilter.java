package com.muhammetozendev.springsecurityexample.securityconfig.jwt;

import com.muhammetozendev.springsecurityexample.securityconfig.AbstractAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationFilter {

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);

    setRequestMatcher(
      request -> !request.getRequestURI().startsWith("/auth")
    );
  }

  @Override
  public AuthenticationConverter getAuthenticationConverter() {
    return request -> {
      String authorizationHeader = request.getHeader("Authorization");
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        return new JwtAuthenticationToken(authorizationHeader.substring(7));
      }
      return null;
    };
  }

}

