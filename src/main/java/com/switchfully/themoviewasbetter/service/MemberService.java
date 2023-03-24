package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.mapper.MemberMapper;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.security.Feature;
import com.switchfully.themoviewasbetter.security.SecurityService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_USERS;
import static com.switchfully.themoviewasbetter.security.Role.ADMIN;
import static com.switchfully.themoviewasbetter.security.Role.LIBRARIAN;

@Service
public class MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;
    private final SecurityService securityService;

    public MemberService(MemberRepository repository, MemberMapper mapper, SecurityService securityService) {
        this.repository = repository;
        this.mapper = mapper;
        this.securityService = securityService;
    }

    public List<MemberDTO> findAll(String authorization) {
        securityService.validateAuthorization(authorization, GET_ALL_USERS);
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public MemberDTO findByEmail(String email) {
        return mapper.toDto(repository.findByEmail(email));
    }

    public MemberDTO create(String authorization, CreateMemberDTO newMember) {
        if (newMember.getRole() == ADMIN || newMember.getRole() == LIBRARIAN) {
            securityService.validateAuthorization(authorization, Feature.REGISTER_ADMIN);
        }
        Member memberToSave = mapper.toDomain(newMember);
        return mapper.toDto(repository.create(memberToSave));
    }
}
