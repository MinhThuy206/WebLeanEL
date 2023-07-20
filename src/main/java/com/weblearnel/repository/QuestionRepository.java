package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
