package com.likelion.team8_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import com.likelion.team8_backend.dto.MemberDTO;
import com.likelion.team8_backend.service.MemberService;
import org.springframework.http.ResponseEntity;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity<ApiResponse> save(@RequestBody MemberDTO memberDTO) {
        boolean isExistingEmail = memberService.checkExistingEmail(memberDTO.getUserEmail());

        boolean isValidEmail = memberService.isValidEmail(memberDTO.getUserEmail());

        if (isExistingEmail) {
            ApiResponse response = new ApiResponse(400,
                    "Membership registration failed. This is a duplicate member.");
//            ApiResponse response = new ApiResponse(400, "회원가입 실패. 중복회원입니다.");
            return ResponseEntity.ok(response);
        } else if (!isValidEmail) {
            ApiResponse response = new ApiResponse(406, "Invalid e-mail format.");
//            ApiResponse response = new ApiResponse(406, "유효하지 않은 이메일 형식입니다.");
            return ResponseEntity.ok(response);
        } else {
            memberService.save(memberDTO);
            ApiResponse response = new ApiResponse(200, "join succeed");
//            ApiResponse response = new ApiResponse(200, "회원가입 완료");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse> login(@RequestBody MemberDTO memberDTO) { // , HttpSession session
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            // 로그인 성공
//            session.setAttribute("loginEmail", loginResult.getUserEmail());
            ApiResponse response = new ApiResponse(200, "login succeed");
//            ApiResponse response = new ApiResponse(200, "로그인 성공");
            return ResponseEntity.ok(response);
        } else {
            // 로그인 실패
            ApiResponse response = new ApiResponse(402, "login failed");
//            ApiResponse response = new ApiResponse(402, "로그인 실패");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("logout")
    public ResponseEntity<ApiResponse> logout(@RequestBody MemberDTO memberDTO) {
        // 회원이 존재하지 않는 경우
        if (memberDTO == null) {
            ApiResponse response = new ApiResponse(402, "logout failed");
            return ResponseEntity.badRequest().body(response);
        }

        String userEmail = memberDTO.getUserEmail();
        String userID = memberDTO.getUserID();
        String userPassword = memberDTO.getUserPassword();
        boolean isExistingEmail = memberService.checkExistingEmail(userEmail);

        if (isExistingEmail) {
            boolean isLogoutSuccessful = memberService.logout(userEmail, userID, userPassword);
            if (isLogoutSuccessful) {
                ApiResponse response = new ApiResponse(200, "logout succeed");
                return ResponseEntity.ok(response);
            } else {
                ApiResponse response = new ApiResponse(402, "logout failed");
                return ResponseEntity.ok(response);
            }
        } else {
            ApiResponse response = new ApiResponse(402, "logout failed");
            return ResponseEntity.ok(response);
        }
    }
    @PostMapping("deleteuser")
    public ResponseEntity<ApiResponse> deleteMember(@RequestBody MemberDTO memberDTO) {
        String userID = memberDTO.getUserID();
        String userPassword = memberDTO.getUserPassword();

        boolean isDeleted = memberService.deleteMember(userID, userPassword);
        if (isDeleted) {
            ApiResponse response = new ApiResponse(200,
                    "Membership withdrawal successful");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(402, "Membership withdrawal failed");
            return ResponseEntity.ok(response);
        }
    }
}
