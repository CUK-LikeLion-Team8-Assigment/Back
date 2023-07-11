package com.likelion.team8_backend.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvalutaionDto {
    @NotNull
    private Long evaluationID;
    @NotNull
    private String userID;
    @NotNull
    private String lectureName;
    @NotNull
    private String professorName;
    @NotNull
    private String lectureYear;
    @NotNull
    private String semesterDivide;
    @NotNull
    private String lectureDivide;
    @NotNull
    private String evaluationTitle;
    @NotNull
    private String evaluationContent;
    @NotNull
    private String totalScore;
    @NotNull
    private String creditScore;
    @NotNull
    private String comfortableScore;
    @NotNull
    private String lectureScore;
    @NotNull
    private Integer likeCount;
    @NotNull
    private Date createdAt;
    @NotNull
    private Date updatedAt;
}
