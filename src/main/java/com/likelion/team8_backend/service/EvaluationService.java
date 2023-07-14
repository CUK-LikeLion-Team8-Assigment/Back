package com.likelion.team8_backend.service;

import com.likelion.team8_backend.domain.Evaluation;
import com.likelion.team8_backend.dto.*;
import com.likelion.team8_backend.repository.EvaluationRepository;
import com.likelion.team8_backend.repository.LikeyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;
    //게시물 작성
    @Transactional
    public Evaluation write(WriteRequest writeRequest){

        Evaluation evaluation = Evaluation.builder()
                .userId(writeRequest.getUserId())
                .lectureName(writeRequest.getLectureName())
                .professorName(writeRequest.getProfessorName())
                .lectureYear(writeRequest.getLectureYear())
                .semesterDivide(writeRequest.getSemesterDivide())
                .lectureDivide(writeRequest.getLectureDivide())
                .evaluationTitle(writeRequest.getEvaluationTitle())
                .evaluationContent(writeRequest.getEvaluationContent())
                .totalScore(writeRequest.getTotalScore())
                .creditScore(writeRequest.getCreditScore())
                .comfortableScore(writeRequest.getComfortableScore())
                .lectureScore(writeRequest.getLectureScore())
                .likeCount(0)
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        return evaluationRepository.save(evaluation);
    }

    //게시물 상세 조회
    @Transactional
    public EvalutaionDto getEvaluation(Long id){
        Evaluation evaluation = evaluationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Evaluation not found"));;

        return EvalutaionDto.builder()
                .id(id)
                .userId(evaluation.getUserId())
                .lectureName(evaluation.getLectureName())
                .professorName(evaluation.getProfessorName())
                .lectureYear(evaluation.getLectureYear())
                .semesterDivide(evaluation.getSemesterDivide())
                .lectureDivide(evaluation.getLectureDivide())
                .evaluationTitle(evaluation.getEvaluationTitle())
                .evaluationContent(evaluation.getEvaluationContent())
                .totalScore(evaluation.getTotalScore())
                .creditScore(evaluation.getCreditScore())
                .comfortableScore(evaluation.getComfortableScore())
                .lectureScore(evaluation.getLectureScore())
                .likeCount(evaluation.getLikeCount())
                .createdAt(evaluation.getCreatedAt())
                .updatedAt(evaluation.getUpdatedAt())
                .build();
    }

    //게시물 전체 조회


    //게시물 수정
    @Transactional
    public void modify(ModifyRequest request, Long id){

        Optional<Evaluation> selected = evaluationRepository.findById(id);

        //아이디 패스워드 일치 확인? 세션 있으면 확인할 필요 없어보임
        //게시물이 존재하는 경우
        if(selected.isPresent()){
            //작성자와 글을 수정하는 사람이 동일인인지 확인
            if(selected.get().getUserId().equals(request.getUserId())){
                Evaluation evaluation = Evaluation.builder()
                        .id(id)
                        .userId(request.getUserId())
                        .lectureName(selected.get().getLectureName())
                        .professorName(selected.get().getProfessorName())
                        .lectureYear(request.getLectureYear())
                        .semesterDivide(selected.get().getSemesterDivide())
                        .lectureDivide(selected.get().getLectureDivide())
                        .evaluationTitle(selected.get().getEvaluationTitle())
                        .evaluationContent(selected.get().getEvaluationContent())
                        .totalScore(request.getTotalScore())
                        .creditScore(selected.get().getCreditScore())
                        .comfortableScore(selected.get().getComfortableScore())
                        .lectureScore(selected.get().getLectureScore())
                        .likeCount(selected.get().getLikeCount())
                        .createdAt(selected.get().getCreatedAt())
                        .updatedAt(new Date())
                        .build();
                evaluationRepository.save(evaluation);
            }
        }
   }

    //게시물 삭제
    public void delete(DeleteRequest request, Long id){

        Evaluation evaluation = evaluationRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Evaluation not found"));;

        if(evaluation.getUserId().equals(request.getUserId())){
            evaluationRepository.delete(evaluation);
        }
    }

    @Autowired
    LikeyRepository likeyRepository;
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
