package com.likelion.team8_backend.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WriteRequest {

    @NotNull
    private String userId;
    @NotNull
    private String lectureName;
    @NotNull
    private String professorName;
    @NotNull
    private Integer lectureYear;
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

    private Integer likeCount;

    private Date createdAt;

    private Date updatedAt;

}
