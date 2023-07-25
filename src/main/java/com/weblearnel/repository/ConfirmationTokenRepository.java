package com.weblearnel.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.weblearnel.model.ConfirmationToken;

@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE confirmation_token c SET c.confirmed_at = ?2 WHERE c.token = ?1", nativeQuery = true)
    void updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
