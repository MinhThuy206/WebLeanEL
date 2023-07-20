package com.weblearnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weblearnel.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
