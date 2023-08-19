package com.weblearnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT * FROM user_answer a WHERE a.fk_user_id = ?1", nativeQuery = true)
    List<Answer> findByUserId(long user_id);

}
