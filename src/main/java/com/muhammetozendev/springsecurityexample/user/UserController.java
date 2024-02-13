package com.muhammetozendev.springsecurityexample.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @GetMapping("user")
  @PreAuthorize("hasRole('USER')")
  public String getUser() {
    return "Hello user!";
  }

  @GetMapping("admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String getAdmin() {
    return "Hello admin!";
  }
}
