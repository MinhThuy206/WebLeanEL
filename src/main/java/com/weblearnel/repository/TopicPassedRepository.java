package com.weblearnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.weblearnel.model.TopicPassed;

public interface TopicPassedRepository extends JpaRepository<TopicPassed, Long>{
    String getTopic = "SELECT * FROM topic_passed tp WHERE tp.topic_passed_id IN ";
    String getTPId = "(SELECT topic_passed_id FROM user_topic_passed utp WHERE utp.user_id = ?1)";
    @Query(value = getTopic + getTPId, nativeQuery = true)
    List<TopicPassed> findByUsers(Long user_id);

   String checkRecordExist = "SELECT COUNT(*) AS row_count FROM user_topic_passed ";
   String condition = "WHERE user_id = ?2 AND topic_passed_id = ?1;";
   @Query(value = checkRecordExist + condition, nativeQuery = true)
    int checkRecordExist(Long topic_passed_id, Long user_id);
}
