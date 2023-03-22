package com.switchfully.themoviewasbetter.mapper;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.MemberDTO;

public class MemberMapper {

    public MemberDTO mapToDTO(Member member){
        return new MemberDTO()
                .setId(member.getId())
                .setINSS(member.getINSS())
                .setEmail(member.getEmail())
                .setLastname(member.getFirstname())
                .setNr(member.getNr())
                .setPostcode(member.getPostcode())
                .setCity(member.getCity())
                .setPassword(member.getPassword())
                .setRole(member.getRole());
    }

    public Member mapToMember(MemberDTO memberDTO){
        return new Member()
                .setId(memberDTO.getId())
                .setINSS(memberDTO.getINSS())
                .setEmail(memberDTO.getEmail())
                .setLastname(memberDTO.getFirstname())
                .setNr(memberDTO.getNr())
                .setPostcode(memberDTO.getPostcode())
                .setCity(memberDTO.getCity())
                .setPassword(memberDTO.getPassword())
                .setRole(memberDTO.getRole());
    }
}
