package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.mapper.MemberMapper;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.security.Role;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberControllerTest {
    private MemberController controller;
    private MemberRepository repository;

    @BeforeEach
    void setUp() {
        controller = new MemberController(new MemberService(new MemberRepository(), new MemberMapper()),
                new SecurityService(new MemberRepository()));
    }

    @Test
    @DisplayName("get all members")
    void getAllMembers() {
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "Pauwels", "",
                "", "", "", "Gent", "XXX");
        Member member1 = new Member("1", "124", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");

        Member member2 = new Member("2", "125", "sven6@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest");


        List<MemberDTO> answer = controller.getAllMembers("cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206WFhY");

        assertThat(answer).hasSize(3);
        assertThat(answer).extracting(MemberDTO::getId).contains(adminMain.getId(), member1.getId(), member2.getId());
        assertThat(answer).extracting(MemberDTO::getEmail).contains(adminMain.getEmail(), member1.getEmail(), member2.getEmail());
        assertThat(answer).extracting(MemberDTO::getLastname).contains(adminMain.getLastname(), member1.getLastname(), member2.getLastname());
        assertThat(answer).extracting(MemberDTO::getFirstname).contains(adminMain.getFirstname(), member1.getFirstname(), member2.getFirstname());
        assertThat(answer).extracting(MemberDTO::getStreetName).contains(adminMain.getStreetName(), member1.getStreetName(), member2.getStreetName());
        assertThat(answer).extracting(MemberDTO::getNr).contains(adminMain.getNr(), member1.getNr(), member2.getNr());
        assertThat(answer).extracting(MemberDTO::getPostcode).contains(adminMain.getPostcode(), member1.getPostcode(), member2.getPostcode());
        assertThat(answer).extracting(MemberDTO::getCity).contains(adminMain.getCity(), member1.getCity(), member2.getCity());
        assertThat(answer).extracting(MemberDTO::getPassword).contains(adminMain.getPassword(), member1.getPassword(), member2.getPassword());
        assertThat(answer).extracting(MemberDTO::getRole).contains(Role.ADMIN, Role.MEMBER, Role.MEMBER);

    }
}