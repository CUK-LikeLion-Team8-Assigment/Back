package com.likelion.team8_backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelion.team8_backend.controller.EvaluationController;
import com.likelion.team8_backend.dto.WriteRequest;
import com.likelion.team8_backend.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@WebMvcTest(EvaluationController.class)
public class EvaluationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluationService evaluationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostEvaluation() throws Exception {
        // 입력 데이터 생성
        WriteRequest writeRequest = WriteRequest.builder()
                .userID("test")
                .lectureName("Data Structures")
                .professorName("Park Jung Heum")
                .lectureYear("2023")
                .semesterDivide("1st semester")
                .lectureDivide("Major")
                .evaluationTitle("Professor Park Jung Heum, the Best as Always")
                .evaluationContent("The professor is excellent, but I can't keep up. It's not the professor's fault, I'm just not good enough... *sigh*")
                .totalScore("B+")
                .creditScore("B")
                .comfortableScore("C")
                .lectureScore("A")
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        // JSON 형식으로 변환
        String json = objectMapper.writeValueAsString(writeRequest);

        // POST 요청 전송 및 응답 검증
        mockMvc.perform(MockMvcRequestBuilders.post("/evaluation/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result.message").value("success"));
    }
}
