package com.weblearnel.repository;

import com.weblearnel.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
