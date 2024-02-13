package com.muhammetozendev.springsecurityexample.user;

import com.muhammetozendev.springsecurityexample.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(String name);
}
