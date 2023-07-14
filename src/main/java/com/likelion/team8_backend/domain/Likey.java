package com.likelion.team8_backend.domain;

import com.likelion.team8_backend.dto.LikeyDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "likey")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // evaluationId에 해당하는 세터 메서드 추가
    public void setEvaluationId(Long evaluationId) {
        this.evaluation = new Evaluation();
        this.evaluation.setId(evaluationId);
    }

    public LikeyDto toDto() {
        return LikeyDto.builder()
                .id(id)
                .userId(userId)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}


