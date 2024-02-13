package com.muhammetozendev.springsecurityexample.auth;

import com.muhammetozendev.springsecurityexample.auth.dto.LoginReqDto;
import com.muhammetozendev.springsecurityexample.auth.dto.LoginResDto;
import com.muhammetozendev.springsecurityexample.auth.dto.RegisterReqDto;
import com.muhammetozendev.springsecurityexample.auth.dto.RegisterResDto;
import com.muhammetozendev.springsecurityexample.user.RoleRepository;
import com.muhammetozendev.springsecurityexample.user.UserRepository;
import com.muhammetozendev.springsecurityexample.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public RegisterResDto register(RegisterReqDto body) {
    User user = userRepository.findByEmail(body.getEmail()).orElse(null);
    if (user != null) {
      throw new RuntimeException("Email already taken");
    }
    user = new User();
    user.setEmail(body.getEmail());
    user.setPassword(passwordEncoder.encode(body.getPassword()));
    user.setRoles(
      body.getRoles().stream().map(
        r -> roleRepository.findByName(r).orElseThrow(() -> new RuntimeException("Invalid role"))
      ).collect(Collectors.toList())
    );
    userRepository.save(user);
    return new RegisterResDto(user.getId(), user.getEmail());
  }

  public LoginResDto login(LoginReqDto body) {
    User user = userRepository.findByEmail(body.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
    if (!passwordEncoder.matches(body.getPassword(), user.getPassword())) {
      throw new RuntimeException("Passwords do not match");
    }
    String token = jwtService.generateToken(user.getId(), user.getEmail());
    return new LoginResDto(token);
  }
}
