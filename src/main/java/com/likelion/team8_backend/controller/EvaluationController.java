package com.likelion.team8_backend.controller;

import com.likelion.team8_backend.BaseResponse;
import com.likelion.team8_backend.BaseResponseStatus;
import com.likelion.team8_backend.domain.Evaluation;
import com.likelion.team8_backend.domain.Likey;
import com.likelion.team8_backend.dto.EvaluationDto;
import com.likelion.team8_backend.dto.LikeyDto;
import com.likelion.team8_backend.repository.EvaluationRepository;
import com.likelion.team8_backend.repository.LikeyRepository;
import com.likelion.team8_backend.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final EvaluationRepository evaluationRepository;
    private final LikeyRepository likeyRepository;

    @Autowired
    public EvaluationController(EvaluationService evaluationService, EvaluationRepository evaluationRepository, LikeyRepository likeyRepository) {
        this.evaluationService = evaluationService;
        this.evaluationRepository = evaluationRepository;
        this.likeyRepository = likeyRepository;
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
    public BaseResponse<LikeyDto> likeEvaluation(@RequestParam("evaluationId") Long evaluationId,
                                                 @RequestParam("userId") String userId) {
        Optional<Evaluation> evaluationOptional = evaluationRepository.findById(evaluationId);
        if (evaluationOptional.isEmpty()) {
            return new BaseResponse<>(BaseResponseStatus.NOT_FOUND);
        }

        Evaluation evaluation = evaluationOptional.get();
        Optional<Likey> existingLikey = likeyRepository.findByEvaluation_IdAndUserId(evaluationId, userId);

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
