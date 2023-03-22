package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_USERS;

@RestController
@RequestMapping("member")
public class MemberController {
    private final MemberService memberService;
    private final SecurityService securityService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService service, SecurityService securityService) {
        this.memberService = service;

        this.securityService = securityService;
    }

    @GetMapping(path = "allmembers")
    public List<MemberDTO> getAllMembers(@RequestHeader String authorization) {
        logger.error("prelog");
        securityService.validateAuthorization(authorization, GET_ALL_USERS);
        logger.error("postlog");
        return memberService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public MemberDTO getMember(@PathVariable("id") String id) {
        return memberService.getMemberbyDTO(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/make")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO saveMember(@RequestBody MemberDTO newMember) {
        return memberService.saveMember(newMember);

    }
}
