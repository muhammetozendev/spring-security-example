package com.muhammetozendev.springsecurityexample.auth.dto;

import com.muhammetozendev.springsecurityexample.user.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDto {
  @Email
  private String email;

  @NotNull
  private String password;

  @NotNull
  private List<String> roles;
}
