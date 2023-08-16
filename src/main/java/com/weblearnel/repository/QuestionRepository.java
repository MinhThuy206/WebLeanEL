package com.weblearnel.repository;

import com.weblearnel.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.Question;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM question q WHERE q.fk_level_id = ?1", nativeQuery = true)
    List<Question> findByLevel(Long level_id);

}
