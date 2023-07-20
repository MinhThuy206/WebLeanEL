package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.ExamTopic;

public interface ExamTopicRepository extends JpaRepository<ExamTopic, Long>{
    
}
