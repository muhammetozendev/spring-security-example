package com.muhammetozendev.springsecurityexample.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {
  @NotNull
  @Email
  private String email;

  @NotNull
  private String password;
}
