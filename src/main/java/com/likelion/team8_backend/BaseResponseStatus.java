package com.likelion.team8_backend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
@AllArgsConstructor
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(), "success."),

    /**
     * 404 : 요청한 리소스를 찾을 수 없음
     */
    NOT_FOUND(false, HttpStatus.NOT_FOUND.value(), "Not found.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    public String toString() {
        return "{" +
                "\"isSuccess\" : " + "\""+isSuccess+"\"" +
                "\"code\" : " + "\""+code+"\"" +
                "\"message\" : " + "\""+message+"\"" +
                "}";
    }
}

