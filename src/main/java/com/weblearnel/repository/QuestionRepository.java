package com.weblearnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question q WHERE q.fk_level_id = ?1", nativeQuery = true)
    List<Question> findByLevel(Long level_id);

   

}
