package com.weblearnel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.weblearnel.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE user u SET u.enabled = TRUE WHERE u.email = ?1", nativeQuery = true)
    void enableUser(String email);
}
