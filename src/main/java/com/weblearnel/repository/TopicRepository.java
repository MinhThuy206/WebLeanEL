package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query(value = "SELECT * FROM topic t WHERE t.name = ?1 LIMIT 1", nativeQuery = true)
    Topic findByName(String topicName);

}
