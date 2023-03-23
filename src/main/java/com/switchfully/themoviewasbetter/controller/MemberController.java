package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.ApiError;
import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.exceptions.UnauthorizedException;
import com.switchfully.themoviewasbetter.exceptions.UnknownUserException;
import com.switchfully.themoviewasbetter.exceptions.WrongPasswordException;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
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
    public MemberDTO saveMember(@RequestBody CreateMemberDTO newMember) {
        return memberService.save(newMember);
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO saveAdmin(@RequestBody MemberDTO newMember, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, REGISTER_ADMIN);
        return memberService.saveAdmin(newMember);
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public @ResponseBody ResponseEntity<ApiError> noAuthorizationProvided() {
        return new ResponseEntity<>(new ApiError(HttpStatus.FORBIDDEN.value(), "Required header 'Authorization' is not present"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({WrongPasswordException.class, UnknownUserException.class, UnauthorizedException.class})
    public @ResponseBody ResponseEntity<ApiError> invalidAuthorizationEntry() {
        return new ResponseEntity<>(new ApiError(HttpStatus.FORBIDDEN.value(), "Invalid authorization"), HttpStatus.FORBIDDEN);
    }
}
