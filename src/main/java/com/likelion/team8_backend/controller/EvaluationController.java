package com.likelion.team8_backend.controller;

import com.likelion.team8_backend.dto.*;
import com.likelion.team8_backend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;

    //게시물 작성
    @PostMapping("/post")
    public ResponseEntity<Response> post(@RequestBody WriteRequest writeRequest){

        evaluationService.write(writeRequest);

        Response response = Response.builder()
                .code("200")
                .result(Response.Result.builder()
                        .message("create")
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시물 수정
    @PatchMapping("/modify/{evaluationID}")
    public ResponseEntity<Response> modify(@RequestBody ModifyRequest request, @PathVariable Long evaluationID){

        evaluationService.modify(request, evaluationID);

        Response response = Response.builder()
                .code("200")
                .result(Response.Result.builder()
                        .message("update")
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시물 상세 조회
    @GetMapping("/{evaluationID}")
    public EvalutaionDto getEvaluation(@PathVariable Long evaluationID){
        return evaluationService.getEvaluation(evaluationID);
    }

    //게시물 삭제
    @DeleteMapping("/delete/{evaluationID}")
    public ResponseEntity<Response> delete(@RequestBody DeleteRequest request, @PathVariable Long evaluationID){
        evaluationService.delete(request, evaluationID);
        Response response = Response.builder()
                .code("204")
                .result(Response.Result.builder()
                        .message("delete")
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
