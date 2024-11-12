package com.weblearnel.user.repo;

import com.weblearnel.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.username LIKE :username OR u.email LIKE :email")
    Page<User> getUsersByConditions(@Param("username") String username, @Param("email") String email, Pageable pageable);
}
