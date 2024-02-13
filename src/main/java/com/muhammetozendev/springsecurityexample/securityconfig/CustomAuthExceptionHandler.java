package com.muhammetozendev.springsecurityexample.securityconfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAuthExceptionHandler implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    Boolean isAuthenticated = (Boolean) request.getAttribute("isAuthenticated");
    if (isAuthenticated != null && isAuthenticated) {
      response.setStatus(403);
      response.getWriter().write("Authorization failure");
    } else {
      response.setStatus(401);
      response.getWriter().write("Authentication failure");
    }
  }
}