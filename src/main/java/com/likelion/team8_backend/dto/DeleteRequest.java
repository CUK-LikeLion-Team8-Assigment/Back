package com.likelion.team8_backend.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class DeleteRequest {
    @NotNull
    private String userId;

    @NotNull
    private String userEmail;
}
