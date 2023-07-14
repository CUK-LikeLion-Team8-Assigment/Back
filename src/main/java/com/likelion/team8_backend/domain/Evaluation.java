package com.likelion.team8_backend.domain;

import com.likelion.team8_backend.dto.EvaluationDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evaluation")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "evaluation_id")
    private Long evaluationId;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL)
    private List<Likey> likeys;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "lecture_name")
    private String lectureName;

    @Column(name = "professor_name")
    private String professorName;

    @Column(name = "lecture_year")
    private Integer lectureYear;

    @Column(name = "semester_divide")
    private String semesterDivide;

    @Column(name = "lecture_divide")
    private String lectureDivide;

    @Column(name = "evaluation_title")
    private String evaluationTitle;

    @Column(name = "evaluation_content")
    private String evaluationContent;

    @Column(name = "total_score")
    private Integer totalScore;

    @Column(name = "credit_score")
    private Integer creditScore;

    @Column(name = "comfortable_score")
    private Integer comfortableScore;

    @Column(name = "lecture_score")
    private Integer lectureScore;

    @Column(name = "like_count")
    private Integer likeCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public EvaluationDto toDto(){
        return EvaluationDto.builder()
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
