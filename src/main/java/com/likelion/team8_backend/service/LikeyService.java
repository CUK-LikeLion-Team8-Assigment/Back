package com.likelion.team8_backend.service;

import com.likelion.team8_backend.domain.Likey;
import com.likelion.team8_backend.dto.LikeyDto;
import com.likelion.team8_backend.repository.LikeyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeyService {
    private final LikeyRepository likeyRepository;
    private final Logger logger = LoggerFactory.getLogger(LikeyService.class);

    @Transactional
    public LikeyDto likeEvaluation(Long Id, String userId) {
        Optional<Likey> existingLikey = likeyRepository.findByIdAndUserId(Id, userId);

        if (existingLikey.isPresent()) {
            // 이미 좋아요, likey 삭제
            Likey likey = existingLikey.get();
            likeyRepository.delete(likey);
            return null;
        } else {
            // 좋아요를 추가합니다.
            Likey newLikey = new Likey();
            newLikey.setId(Id);
            newLikey.setUserId(userId);
            newLikey.setCreatedAt(LocalDateTime.now());
            newLikey.setUpdatedAt(LocalDateTime.now());
            Likey savedLikey = likeyRepository.save(newLikey);
            return savedLikey.toDto();
        }
    }
}
