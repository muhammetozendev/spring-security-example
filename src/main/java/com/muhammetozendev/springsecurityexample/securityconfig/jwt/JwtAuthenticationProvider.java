package com.muhammetozendev.springsecurityexample.securityconfig.jwt;

import com.muhammetozendev.springsecurityexample.auth.JwtService;
import com.muhammetozendev.springsecurityexample.user.UserRepository;
import com.muhammetozendev.springsecurityexample.user.entity.Role;
import com.muhammetozendev.springsecurityexample.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    System.out.println("Jwt Auth Provider");
    String token = (String) authentication.getCredentials();
    if (!jwtService.isTokenValid(token)) {
      System.out.println("Invalid jwt");
      return null;
    }
    String strId = jwtService.extractId(token);
    User user = userRepository.findUserById(Integer.valueOf(strId)).orElse(null);
    if (user == null) return null;
    List<Role> roles = user.getRoles();
    List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    return new JwtAuthenticationToken(user, authorities);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(JwtAuthenticationToken.class);
  }
}
