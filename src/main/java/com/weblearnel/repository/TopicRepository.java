package com.weblearnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query(value = "SELECT * FROM topic t WHERE t.name = ?1 LIMIT 1", nativeQuery = true)
    Topic findByName(String topicName);

    String getTopic = "SELECT * FROM topic tp WHERE tp.tp_id IN ";
    String getTPId = "(SELECT tp_id FROM user_topic utp WHERE utp.user_id = ?1)";
    @Query(value = getTopic + getTPId, nativeQuery = true)
    List<Topic> findByUsers(Long user_id);

   String checkRecordExist = "SELECT COUNT(*) AS row_count FROM user_topic ";
   String condition = "WHERE user_id = ?2 AND tp_id = ?1";
   @Query(value = checkRecordExist + condition, nativeQuery = true)
    int checkRecordExist(Long topic_id, Long user_id);

}
