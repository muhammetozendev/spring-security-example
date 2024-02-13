package com.muhammetozendev.springsecurityexample.securityconfig;

import com.muhammetozendev.springsecurityexample.securityconfig.jwt.JwtAuthenticationFilter;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .httpBasic(basic -> basic.disable())
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .exceptionHandling(e -> e.authenticationEntryPoint(new CustomAuthExceptionHandler()))
      // Protect routes
      .authorizeHttpRequests(
        auth -> auth
          .requestMatchers("/auth/**").permitAll()
          .anyRequest().authenticated()
      )
      // Register authentication providers
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


    return http.build();
  }

  @Bean
  public WebMvcConfigurer corsConfig() {
    return new WebMvcConfigurer() {
      public void addCorsMappings(@NotNull CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedMethods("*")
          .allowedOrigins("*");
      }
    };
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
