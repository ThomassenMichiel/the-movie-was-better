package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.ApiError;
import com.switchfully.themoviewasbetter.dto.CreateMemberDTO;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.exceptions.UnauthorizedException;
import com.switchfully.themoviewasbetter.exceptions.UnknownUserException;
import com.switchfully.themoviewasbetter.exceptions.WrongPasswordException;
import com.switchfully.themoviewasbetter.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;


    public MemberController(MemberService service) {
        this.memberService = service;
    }

    @GetMapping()
    public List<MemberDTO> findAll(@RequestHeader String authorization) {
        return memberService.findAll(authorization);
    }

    @GetMapping(path = "/{id}")
    public MemberDTO findById(@PathVariable("id") String id) {
        return memberService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO create(@RequestHeader String authorization, @RequestBody CreateMemberDTO newMember) {
        return memberService.save(authorization, newMember);
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
