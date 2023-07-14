package com.likelion.team8_backend.service;

import com.likelion.team8_backend.domain.Evaluation;
import com.likelion.team8_backend.dto.EvaluationDto;
import com.likelion.team8_backend.repository.EvaluationRepository;
import com.likelion.team8_backend.repository.LikeyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final LikeyRepository likeyRepository;
    private final Logger logger = LoggerFactory.getLogger(EvaluationService.class);

    @Transactional
    public List<EvaluationDto> search(String keyword, String keyword2, String keyword3) {
        List<Evaluation> postsList = evaluationRepository.findByConditions(keyword, keyword2, keyword3);

        List<EvaluationDto> evaluationDtoList = postsList.stream()
                .map(evaluation -> {
                    EvaluationDto evaluationDto = evaluation.toDto();
                    int likeCount = likeyRepository.countById(evaluation.getId());
                    evaluationDto.setLikeCount(likeCount);
                    return evaluationDto;
                })
                .collect(Collectors.toList());

        return evaluationDtoList;
    }
}

