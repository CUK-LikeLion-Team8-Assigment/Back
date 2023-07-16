package com.likelion.team8_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import com.likelion.team8_backend.dto.MemberDTO;
import com.likelion.team8_backend.service.MemberService;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<ApiResponse> login(@RequestBody MemberDTO memberDTO,  HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);

        if (loginResult != null) {
            // 로그인 성공
            ApiResponse response = new ApiResponse(200, "login succeed");

            // session에 해당 ID 저장
            session.setAttribute("userID", loginResult.getUserID());
            return ResponseEntity.ok(response);
        } else {
            // 로그인 실패
            ApiResponse response = new ApiResponse(402, "login failed");
            return ResponseEntity.ok(response);
        }
    }
    @PatchMapping("logout")
    public ResponseEntity<ApiResponse> logout(@RequestBody MemberDTO memberDTO, HttpSession session) {
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
            // userLogin 값이 1인지 확인
            boolean isUserLoggedIn = memberService.checkUserLogin(userEmail, userID, userPassword);

            if (isUserLoggedIn) {
                boolean isLogoutSuccessful = memberService.logout(userEmail, userID, userPassword);

                if (isLogoutSuccessful) {
                    ApiResponse response = new ApiResponse(200, "logout succeed");
                    // 로그아웃시 해당 userID의 내용을 세션에서 삭제
                    session.removeAttribute("userID");
                    return ResponseEntity.ok(response);
                } else {
                    ApiResponse response = new ApiResponse(402, "logout failed");
                    return ResponseEntity.ok(response);
                }
            } else {
                ApiResponse response = new ApiResponse(402, "logout failed");
                return ResponseEntity.ok(response);
            }
        } else {
            ApiResponse response = new ApiResponse(402, "logout failed");
            return ResponseEntity.ok(response);
        }
    }
    @DeleteMapping("deleteuser")
    public ResponseEntity<ApiResponse> deleteMember(@RequestBody MemberDTO memberDTO) {
        String userID = memberDTO.getUserID();
        String userPassword = memberDTO.getUserPassword();

        boolean isDeleted = memberService.deleteMember(userID, userPassword);
        if (isDeleted) {
            ApiResponse response = new ApiResponse(200, "Membership withdrawal successful");
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(402, "Membership withdrawal failed");
            return ResponseEntity.ok(response);
        }
    }

}
