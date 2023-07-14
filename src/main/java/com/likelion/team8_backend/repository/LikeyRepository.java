package com.likelion.team8_backend.repository;

import com.likelion.team8_backend.domain.Likey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeyRepository extends JpaRepository<Likey, Long> {
    Optional<Likey> findByEvaluation_IdAndUserId(Long evaluationId, String userId);

    int countByEvaluation_Id(Long evaluationId);
}


