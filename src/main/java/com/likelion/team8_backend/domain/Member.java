package com.likelion.team8_backend.domain;

import com.likelion.team8_backend.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_table")
public class Member {
    @Id //  PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment

    private Long user_index;
    @Column
    private String userID;
    @Column
    private String userPassword;
    @Column(unique = true) // unique라는 제약조건을 추가
    private String userEmail;
    @Column
    private Boolean userLogin;

    public static Member toMember (MemberDTO memberDTO){
        Member member = new Member();
        member.setUserID(memberDTO.getUserID());
        member.setUserPassword(memberDTO.getUserPassword());
        member.setUserEmail(memberDTO.getUserEmail());
        member.setUserLogin(memberDTO.getUserLogin());
        return member;
    }
    public void loginSuccess() {
        this.setUserLogin(true);
    }
    public boolean isUserLogin() {
        return userLogin;
    }
}
