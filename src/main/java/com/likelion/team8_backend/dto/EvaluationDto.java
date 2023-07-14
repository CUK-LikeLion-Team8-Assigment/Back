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
public class EvaluationDto {
    private Long id;

    private Long evaluationId;

    private String userId;

    private String lectureName;

    private String professorName;

    private Integer lectureYear;

    private String semesterDivide;

    private String lectureDivide;

    private String evaluationTitle;

    private String evaluationContent;

    private Integer totalScore;

    private Integer creditScore;

    private Integer comfortableScore;

    private Integer lectureScore;

    private Integer likeCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Evaluation toEntity(){
        return Evaluation.builder()
                .id(id)
                .evaluationId(evaluationId)
                .userId(userId)
                .lectureName(lectureName)
                .professorName(professorName)
                .lectureYear(lectureYear)
                .semesterDivide(semesterDivide)
                .lectureDivide(lectureDivide)
                .evaluationTitle(evaluationTitle)
                .evaluationContent(evaluationContent)
                .totalScore(totalScore)
                .creditScore(creditScore)
                .comfortableScore(comfortableScore)
                .lectureScore(lectureScore)
                .likeCount(likeCount)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
