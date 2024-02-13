package com.muhammetozendev.springsecurityexample.user;

import com.muhammetozendev.springsecurityexample.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles r WHERE u.id = :id")
  Optional<User> findUserById(Integer id);

  Optional<User> findByEmail(String email);
}

