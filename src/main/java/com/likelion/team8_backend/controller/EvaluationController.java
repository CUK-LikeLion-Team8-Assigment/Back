package com.likelion.team8_backend.controller;

import com.likelion.team8_backend.BaseResponse;
import com.likelion.team8_backend.BaseResponseStatus;
import com.likelion.team8_backend.domain.Evaluation;
import com.likelion.team8_backend.domain.Likey;
import com.likelion.team8_backend.dto.*;
import com.likelion.team8_backend.repository.EvaluationRepository;
import com.likelion.team8_backend.repository.LikeyRepository;
import com.likelion.team8_backend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    EvaluationService evaluationService;
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    LikeyRepository likeyRepository;


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
    @PatchMapping("/modify/{id}")
    public ResponseEntity<Response> modify(@RequestBody ModifyRequest request, @PathVariable Long id){

        evaluationService.modify(request, id);

        Response response = Response.builder()
                .code("200")
                .result(Response.Result.builder()
                        .message("update")
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //게시물 상세 조회
    @GetMapping("/{id}")
    public EvalutaionDto getEvaluation(@PathVariable Long id){
        return evaluationService.getEvaluation(id);
    }

    //게시물 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@RequestBody DeleteRequest request, @PathVariable Long id) {
        evaluationService.delete(request, id);
        Response response = Response.builder()
                .code("204")
                .result(Response.Result.builder()
                        .message("delete")
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* 검색기능 */
    @GetMapping("/search")
    public BaseResponse<List<EvaluationDto>> search(@RequestParam("lectureDivide") String keyword,
                                                    @RequestParam("searchType") String keyword2,
                                                    @RequestParam("search") String keyword3)
    {
        List<EvaluationDto> searchList = evaluationService.search(keyword, keyword2, keyword3);
        return new BaseResponse<>(searchList);
    }

    /* 추천기능 */
    @PostMapping("/like")
    public BaseResponse<LikeyDto> likeEvaluation(@RequestParam("Id") Long Id,
                                                 @RequestParam("userId") String userId) {
        Optional<Evaluation> evaluationOptional = evaluationRepository.findById(Id);
        if (evaluationOptional.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND);
        }

        Evaluation evaluation = evaluationOptional.get();
        Optional<Likey> existingLikey = likeyRepository.findByIdAndUserId(Id, userId);

        if (existingLikey.isPresent()) {
            // 이미 좋아요, likey 삭제
            Likey likey = existingLikey.get();
            likeyRepository.delete(likey);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } else {
            // 좋아요
            Likey newLikey = new Likey();
            newLikey.setEvaluation(evaluation); // Evaluation 설정
            newLikey.setUserId(userId);
            newLikey.setCreatedAt(LocalDateTime.now());
            newLikey.setUpdatedAt(LocalDateTime.now());
            Likey savedLikey = likeyRepository.save(newLikey);
            return new BaseResponse<>(savedLikey.toDto());
        }
    }
}
