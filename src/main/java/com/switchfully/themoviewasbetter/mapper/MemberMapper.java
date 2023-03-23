package com.switchfully.themoviewasbetter.mapper;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDTO mapToDTO(Member member){
        return new MemberDTO()
                .setId(member.getId())
                .setEmail(member.getEmail())
                .setLastname(member.getLastname())
                .setFirstname(member.getFirstname())
                .setStreetName(member.getStreetName())
                .setNr(member.getNr())
                .setPostcode(member.getPostcode())
                .setCity(member.getCity())
                .setPassword(member.getPassword())
                .setRole(member.getRole());
    }

    public Member mapToMember(MemberDTO memberDTO){
        return new Member()
                .setId(memberDTO.getId())
                .setEmail(memberDTO.getEmail())
                .setLastname(memberDTO.getLastname())
                .setFirstname(memberDTO.getFirstname())
                .setStreetName(memberDTO.getStreetName())
                .setNr(memberDTO.getNr())
                .setPostcode(memberDTO.getPostcode())
                .setCity(memberDTO.getCity())
                .setPassword(memberDTO.getPassword())
                .setRole(memberDTO.getRole());
    }

    public Member mapToMember(CreateMemberDTO memberDTO){
        return new Member()
                .setId(memberDTO.getId())
                .setEmail(memberDTO.getEmail())
                .setLastname(memberDTO.getLastname())
                .setFirstname(memberDTO.getFirstname())
                .setStreetName(memberDTO.getStreetName())
                .setNr(memberDTO.getNr())
                .setPostcode(memberDTO.getPostcode())
                .setCity(memberDTO.getCity())
                .setPassword(memberDTO.getPassword())
                .setRole(memberDTO.getRole());
    }
}
