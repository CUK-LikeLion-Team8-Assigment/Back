package com.likelion.team8_backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import com.likelion.team8_backend.dto.MemberDTO;
import com.likelion.team8_backend.service.MemberService;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;


@CrossOrigin(origins = "http://localhost:3000")

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("join")
    public ResponseEntity<ApiResponse> save(@RequestBody  MemberDTO memberDTO) {
        boolean isExistingEmail = memberService.checkExistingEmail(memberDTO.getUserEmail());

        boolean isValidEmail = memberService.isValidEmail(memberDTO.getUserEmail());

        if (isExistingEmail) {
            ApiResponse response = new ApiResponse(400, "Member registration failed. This is a duplicate member.");
            return ResponseEntity.status(400).body(response);
        } else if (!isValidEmail) {
            ApiResponse response = new ApiResponse(406, "Invalid Email format.");
            return ResponseEntity.status(406).body(response);
        } else {
            memberService.save(memberDTO);
            ApiResponse response = new ApiResponse(200, "Member registration succeed");
            return ResponseEntity.ok(response);
        }
    }
    // , HttpSession session
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
            return ResponseEntity.status(402).body(response);
        }
    }
    // session 값 가지고 오기
    @GetMapping("getSession")
    public ResponseEntity<String> getSessionValue(HttpSession session) {
        String userID = (String) session.getAttribute("userID");

        if (userID != null) {
            return ResponseEntity.ok(userID);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("logout")
    public ResponseEntity<ApiResponse> logout(HttpSession session) {
        // 세션에서 userID 가져오기
        String userID = (String) session.getAttribute("userID");

        if (userID != null) {
            ApiResponse response = new ApiResponse(200, "logout succeed");
            session.removeAttribute("userID");
            return ResponseEntity.ok(response);

        } else {
            ApiResponse response = new ApiResponse(402, "logout failed");
            return ResponseEntity.status(402).body(response);
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
            return ResponseEntity.status(402).body(response);
        }
    }

}
