package com.weblearnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(value = "SELECT * FROM word w WHERE w.name = ?1", nativeQuery = true)
    Word findByName(String content);

    @Query(value = "SELECT * FROM word w WHERE w.fk_tp_id = ?1", nativeQuery = true)
    List<Word> findByTopic(Long topic_id);
}
