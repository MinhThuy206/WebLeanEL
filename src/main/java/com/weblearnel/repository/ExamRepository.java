package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{
    
}
