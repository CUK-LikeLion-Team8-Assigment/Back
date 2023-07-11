package com.likelion.team8_backend.service;

import com.likelion.team8_backend.domain.Evaluation;
import com.likelion.team8_backend.dto.DeleteRequest;
import com.likelion.team8_backend.dto.EvalutaionDto;
import com.likelion.team8_backend.dto.ModifyRequest;
import com.likelion.team8_backend.dto.WriteRequest;
import com.likelion.team8_backend.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;
    //게시물 작성
    @Transactional
    public Evaluation write(WriteRequest writeRequest){

        Evaluation evaluation = Evaluation.builder()
                .userID(writeRequest.getUserID())
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
    public EvalutaionDto getEvaluation(Long evaluationID){
        Evaluation evaluation = evaluationRepository.findById(evaluationID).orElseThrow(() -> new NoSuchElementException("Evaluation not found"));;

        return EvalutaionDto.builder()
                .evaluationID(evaluationID)
                .userID(evaluation.getUserID())
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
    public void modify(ModifyRequest request, Long evaluationID){

        Optional<Evaluation> selected = evaluationRepository.findById(evaluationID);

        //아이디 패스워드 일치 확인? 세션 있으면 확인할 필요 없어보임
        //게시물이 존재하는 경우
        if(selected.isPresent()){
            //작성자와 글을 수정하는 사람이 동일인인지 확인
            if(selected.get().getUserID().equals(request.getUserID())){
                Evaluation evaluation = Evaluation.builder()
                        .evaluationID(evaluationID)
                        .userID(request.getUserID())
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
    public void delete(DeleteRequest request, Long evaluationID){

        Evaluation evaluation = evaluationRepository.findById(evaluationID).orElseThrow(() -> new NoSuchElementException("Evaluation not found"));;

        if(evaluation.getUserID().equals(request.getUserID())){
            evaluationRepository.delete(evaluation);
        }
    }

}
