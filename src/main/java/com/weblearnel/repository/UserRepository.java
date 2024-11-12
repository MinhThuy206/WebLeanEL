package com.weblearnel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.weblearnel.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE user u SET u.enabled = TRUE WHERE u.email = ?1", nativeQuery = true)
    void enableUser(String email);

    String getUser = "SELECT * FROM user u WHERE u.user_id IN ";
    String getUserID = "(SELECT user_id FROM user_topic_passed utp WHERE utp.topic_passed_id = ?1)";
    @Query(value = getUser + getUserID, nativeQuery = true)
    List<User> findUsersFromTopicPassed(Long topicPassed_id);

    String getUsers = "SELECT * FROM user u WHERE u.user_id IN ";
    String getUsersID = "(SELECT user_id FROM user_topic utp WHERE utp.tp_id = ?1)";
    @Query(value = getUsers + getUsersID, nativeQuery = true)
    List<User> findUsersFromTopic(Long topic_id);
}
