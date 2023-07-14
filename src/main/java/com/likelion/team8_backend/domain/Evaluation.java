package com.likelion.team8_backend.domain;

import com.likelion.team8_backend.dto.ModifyRequest;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_evaluation")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationID;

    @Column(nullable = false)
    private String userID;

    @Column(nullable = false)
    private String lectureName;

    @Column(nullable = false)
    private String professorName;

    @Column(nullable = false)
    private String lectureYear;

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

    private Integer likeCount;

    private Date createdAt;

    private Date updatedAt;

}
