package com.spring.security.springsecuritybasic.repository;

import com.spring.security.springsecuritybasic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
