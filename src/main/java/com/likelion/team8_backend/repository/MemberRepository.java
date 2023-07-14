package com.likelion.team8_backend.repository;
import com.likelion.team8_backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface MemberRepository extends JpaRepository <Member, Long>{
    Optional<Member> findByUserEmail(String userEmail);
    Optional<Member> findByUserIDAndUserPassword(String userID, String userPassword);
}
