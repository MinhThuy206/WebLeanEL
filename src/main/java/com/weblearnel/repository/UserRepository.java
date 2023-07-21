package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.weblearnel.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
