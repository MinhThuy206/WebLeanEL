package com.weblearnel.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import com.weblearnel.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long>{
    @Query(value = "SELECT * FROM Result u WHERE u.fk_ex_id = ?1", nativeQuery = true)
    Set<Result> findResultsOfSameExam(@PathVariable Long ex_id);
    
}
