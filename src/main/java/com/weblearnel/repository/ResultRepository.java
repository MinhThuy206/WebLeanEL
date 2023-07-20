package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long>{
    
}
