package com.likelion.team8_backend.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ModifyRequest {

    @NotNull
    private String userID;

    @NotNull
    private String userPassword;

    @NotNull
    private String userEmail;

    @NotNull
    private String lectureYear;

    @NotNull
    private String totalScore;
}
