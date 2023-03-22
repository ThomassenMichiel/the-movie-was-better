package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.mapper.MemberMapper;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {
    private final MemberService service;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService service) {
        this.service = service;

    }

    @GetMapping(path="allmembers")
    public List<MemberDTO> getAllMembers(){
        return service.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public MemberDTO getMember(@PathVariable("id")String id){
        return service.getMemberbyDTO(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/make")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO saveMember(@RequestBody MemberDTO  newMember00){
        return service.saveMember(newMember00);

    }


}
