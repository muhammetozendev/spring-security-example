package com.muhammetozendev.springsecurityexample.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResDto {
  private Integer id;
  private String email;
}
