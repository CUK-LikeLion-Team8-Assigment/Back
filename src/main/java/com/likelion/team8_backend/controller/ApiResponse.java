package com.likelion.team8_backend.controller;

public class ApiResponse {
    private int code;
    private Result result;
    public ApiResponse(int code, String message) {
        this.code = code;
        this.result = new Result(message);
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public Result getResult() {
        return result;
    }
    public void setResult(Result result) {
        this.result = result;
    }
    class Result {
        private String message;
        public Result(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
