package com.likelion.team8_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class Response {

    private String code;
    private Result result;

    @Builder
    public Response(String code, Result result) {
        this.code = code;
        this.result = result;
    }

    public static class Result {
        private String message;

        @Builder
        public Result(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
