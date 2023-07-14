package com.likelion.team8_backend.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ModifyRequest {

    @NotNull
    private String userId;

    @NotNull
    private String userEmail;

    @NotNull
    private Integer lectureYear;

    @NotNull
    private String totalScore;
}
