package com.likelion.team8_backend.domain;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import com.likelion.team8_backend.dto.EvaluationDto;
import java.util.List;

@Entity
@Table(name = "evaluation")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    private List<Likey> likeys;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false)
    private String professorName;

    @Column(nullable = false)
    private Integer lectureYear;

    @Column(nullable = false)
    private String semesterDivide;

    @Column(nullable = false)
    private String lectureDivide;

    @Column(nullable = false)
    private String evaluationTitle;

    @Column(nullable = false)
    private String evaluationContent;

    @Column(nullable = false)
    private String totalScore;

    @Column(nullable = false)
    private String creditScore;

    @Column(nullable = false)
    private String comfortableScore;

    @Column(nullable = false)
    private String lectureScore;

    @Column(nullable = true)
    private Integer likeCount;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationDto toDto(){
        return EvaluationDto.builder()
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
