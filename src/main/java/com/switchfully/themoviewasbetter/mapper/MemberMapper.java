package com.switchfully.themoviewasbetter.mapper;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDTO toDto(Member member){
        return new MemberDTO(member.getEmail(), member.getLastname(), member.getFirstname(), member.getStreetName(), member.getNumber(), member.getPostcode(), member.getCity(), member.getRole());
    }

    public Member toDomain(CreateMemberDTO memberDTO){
        return new Member(memberDTO.getInss(),memberDTO.getEmail(), memberDTO.getLastname(), memberDTO.getFirstname(), memberDTO.getStreetName(), memberDTO.getNumber(), memberDTO.getPostcode(), memberDTO.getCity(), memberDTO.getPassword(), memberDTO.getRole());
    }
}
