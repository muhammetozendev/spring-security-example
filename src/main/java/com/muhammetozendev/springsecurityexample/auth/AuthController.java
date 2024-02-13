package com.muhammetozendev.springsecurityexample.auth;

import com.muhammetozendev.springsecurityexample.auth.dto.LoginReqDto;
import com.muhammetozendev.springsecurityexample.auth.dto.LoginResDto;
import com.muhammetozendev.springsecurityexample.auth.dto.RegisterReqDto;
import com.muhammetozendev.springsecurityexample.auth.dto.RegisterResDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<RegisterResDto> register(@Valid @RequestBody RegisterReqDto body) {
    return new ResponseEntity<>(authService.register(body), HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto body) {
    return new ResponseEntity<>(authService.login(body), HttpStatus.OK);
  }
}
