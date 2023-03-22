package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.mapper.MemberMapper;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {

    private final MemberRepository repository;
    private final MemberMapper mapper;

    public MemberService(MemberRepository repository, MemberMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MemberDTO> getAllUsers(){
        return repository.getAllUsers()
                .stream()
                .map(member -> mapper.mapToDTO(member))
                .toList();
    }

    public MemberDTO getMemberbyDTO(String id) {
        return mapper.mapToDTO(repository.getMember(id));
    }

    public MemberDTO saveMember(MemberDTO newMember){
        Member memberToSave = mapper.mapToMember(newMember);
        return mapper.mapToDTO(repository.registerMember(memberToSave));


    }

}
