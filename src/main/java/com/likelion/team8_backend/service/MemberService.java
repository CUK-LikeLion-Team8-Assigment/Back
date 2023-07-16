package com.likelion.team8_backend.service;

import com.likelion.team8_backend.domain.Member;
import com.likelion.team8_backend.dto.MemberDTO;
import com.likelion.team8_backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity로 변환
        Member member = Member.toMember(memberDTO);
        // 2. repository
        memberRepository.save(member);
        // repository의 save메소드 호출 (조건.entitiy 객체를 넘겨줘야한다)
    }
    // 회원가입시 중복된 이메일인지 체크
    public boolean checkExistingEmail(String userEmail) {
        Optional<Member> existingMember = memberRepository.findByUserEmail(userEmail);
        return existingMember.isPresent();
    }
    // 회원가입시 이메일 유효성 확인
    public boolean isValidEmail(String userEmail) {
        if (userEmail == null || userEmail.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return userEmail.matches(emailRegex);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        // 1. 회원이 입력한 이메일로 DB에서 조회
        // 2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<Member> byUserEmail = memberRepository.findByUserEmail(memberDTO.getUserEmail());
        if (byUserEmail.isPresent()){
            //조회 결과가 있다.(해당 이메일을 가진 회원 정보가 있다.)
            Member member = byUserEmail.get();
            if(member.getUserPassword().equals(memberDTO.getUserPassword())){
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(member);
                member.setUserLogin(true);
                memberRepository.save(member);
//                return  dto;
                return MemberDTO.toMemberDTO(member);
            }else{
                // 비밀번호 불일치 (로그인 실패)
                return null;
            }
        }else{
            // 조회결과가 없다.(해당 이메일을 가진 회원이 없다.)
            return null;
        }
    }

    public boolean logout(String userEmail, String userID, String userPassword) {
        Optional<Member> optionalMember = memberRepository.findByUserEmail(userEmail);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            if (member.getUserID().equals(userID) && member.getUserPassword().equals(userPassword) && member.getUserLogin()) {
                member.setUserLogin(false);
                memberRepository.save(member);
                return true;
            }
        }
        return false;
    }
    // 로그아웃을 할때 사용
    public boolean checkUserLogin(String userEmail, String userID, String userPassword) {
        Optional<Member> optionalMember = memberRepository.findByUserEmail(userEmail);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return member.getUserID().equals(userID) && member.getUserPassword().equals(userPassword) && member.getUserLogin();
        }
        return false;
    }


    public boolean deleteMember(String userID, String userPassword) {
        Optional<Member> optionalMember = memberRepository.findByUserIDAndUserPassword(userID, userPassword);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            memberRepository.delete(member);
            return true;
        }
        return false;
    }


}
