package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.Word;

public interface WordRepository extends JpaRepository<Word, Long> {
    @Query(value = "SELECT name FROM Word w WHERE w.name = ?1", nativeQuery = true)
    Word findByName(String content);
}
