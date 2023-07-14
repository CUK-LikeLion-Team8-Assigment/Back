package com.likelion.team8_backend.repository;

import com.likelion.team8_backend.domain.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    // List<Evaluation> findByLectureDivideContainingAndSearchTypeAndSearch(String keyword, String keyword2, String keyword3);

    @Query("SELECT e FROM Evaluation e WHERE (:lectureDivide IS NULL OR e.lectureDivide = :lectureDivide) " +

            "AND (:search IS NULL OR e.evaluationContent LIKE %:search% OR e.professorName LIKE %:search% " +
            "OR e.semesterDivide LIKE %:search% OR e.evaluationTitle LIKE %:search% OR e.lectureName LIKE %:search%) " +
            "ORDER BY CASE WHEN :searchType = '최신순' THEN e.createdAt END DESC, " +
            "CASE WHEN :searchType = '추천순' THEN e.likeCount END DESC")
    List<Evaluation> findByConditions(@Param("lectureDivide") String lectureDivide,
                                      @Param("searchType") String searchType,
                                      @Param("search") String search);

}
