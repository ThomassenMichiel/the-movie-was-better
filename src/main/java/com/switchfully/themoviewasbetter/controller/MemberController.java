package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_USERS;
import static com.switchfully.themoviewasbetter.security.Feature.REGISTER_ADMIN;

@RestController
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService service, SecurityService securityService) {
        this.memberService = service;
        this.securityService = securityService;
    }

    @GetMapping()
    public List<MemberDTO> getAllMembers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, GET_ALL_USERS);
        return memberService.findAll();
    }

    @GetMapping(path = "/{id}")
    public MemberDTO getMember(@PathVariable("id") String id) {
        return memberService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO saveMember(@RequestBody MemberDTO newMember) {
        return memberService.save(newMember);
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO saveAdmin(@RequestBody MemberDTO newMember, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, REGISTER_ADMIN);
        return memberService.saveAdmin(newMember);
    }
}
