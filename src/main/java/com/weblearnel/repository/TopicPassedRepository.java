package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.TopicPassed;

public interface TopicPassedRepository extends JpaRepository<TopicPassed, Long>{
    
}
