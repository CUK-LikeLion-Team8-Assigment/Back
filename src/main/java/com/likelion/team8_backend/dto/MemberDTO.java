package com.likelion.team8_backend.dto;

import lombok.*;
import com.likelion.team8_backend.domain.Member;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private long user_index;
    private String userID;
    private String userPassword;
    private String userEmail;
    private Boolean userLogin;


    public static MemberDTO toMemberDTO(Member member){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUser_index(member.getUser_index());
        memberDTO.setUserID(member.getUserID());
        memberDTO.setUserPassword(member.getUserPassword());
        memberDTO.setUserEmail(member.getUserEmail());
        memberDTO.setUserLogin(member.getUserLogin());
        return  memberDTO;
    }
}
