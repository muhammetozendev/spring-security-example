package com.muhammetozendev.springsecurityexample.securityconfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public abstract class AbstractAuthenticationFilter extends OncePerRequestFilter {
  private AuthenticationManager authenticationManager;
  private RequestMatcher requestMatcher = AnyRequestMatcher.INSTANCE;


  public AbstractAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  public void setRequestMatcher(RequestMatcher matcher) {
    this.requestMatcher = matcher;
  }

  public abstract AuthenticationConverter getAuthenticationConverter();

  @Override
  protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // Do not attempt to authenticate if authentication is not present
    Authentication authentication = getAuthenticationConverter().convert(request);
    if (authentication == null) {
      filterChain.doFilter(request, response);
      return;
    }
    try {
      // Do not attempt to authenticate if request is already authenticated
      Authentication existingAuthentication = SecurityContextHolder.getContext().getAuthentication();
      if (existingAuthentication != null && existingAuthentication.isAuthenticated()) {
        filterChain.doFilter(request, response);
        return;
      }

      // Perform the authentication and set it in the security context
      Authentication populatedAuthentication = authenticationManager.authenticate(authentication);
      SecurityContextHolder.getContext().setAuthentication(populatedAuthentication);
      request.setAttribute("isAuthenticated", true);
    } catch (Exception e) {
      SecurityContextHolder.getContext().setAuthentication(null);
    }

    // Pass the control to the next filter
    filterChain.doFilter(request, response);
  }
}
