package com.likelion.team8_backend.dto;

import com.likelion.team8_backend.domain.Evaluation;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeyDto {
    private Long id;
    private String userId;
    private Long evaluationId; // evaluationId 추가
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자 수정
    public LikeyDto(Long id, String userId, Evaluation evaluation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.evaluationId = evaluation.getId();
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
