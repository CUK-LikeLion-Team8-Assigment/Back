package com.likelion.team8_backend.dto;

import com.likelion.team8_backend.domain.Evaluation;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {
    private Long id;

    private String userId;

    private String lectureName;

    private String professorName;

    private Integer lectureYear;

    private String semesterDivide;

    private String lectureDivide;

    private String evaluationTitle;

    private String evaluationContent;

    private String totalScore;

    private String creditScore;

    private String comfortableScore;

    private String lectureScore;

    private Integer likeCount;

    private Date createdAt;

    private Date updatedAt;


    public Evaluation toEntity(){
        return Evaluation.builder()
                .id(id)
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
