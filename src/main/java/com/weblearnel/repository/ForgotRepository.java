package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.user.entity.User;

public interface ForgotRepository extends JpaRepository<User, Long> {
User findByEmail(String email);

User findByToken(String token);
}