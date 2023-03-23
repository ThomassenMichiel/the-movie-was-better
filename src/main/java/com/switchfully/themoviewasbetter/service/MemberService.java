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

    public List<MemberDTO> findAll(){
        return repository.getAllUsers()
                .stream()
                .map(mapper::mapToDTO)
                .toList();
    }

    public MemberDTO findById(String id) {
        return mapper.mapToDTO(repository.getMember(id));
    }

    public MemberDTO save(MemberDTO newMember){
        Member memberToSave = mapper.mapToMember(newMember);
        return mapper.mapToDTO(repository.registerMember(memberToSave));
    }

    public MemberDTO saveAdmin(MemberDTO newMember){
        Member memberToSave = mapper.mapToMember(newMember);
        return mapper.mapToDTO(repository.registerAdministrator(memberToSave));
    }
}
