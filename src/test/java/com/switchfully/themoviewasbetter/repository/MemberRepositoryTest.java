package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    private MemberRepository repositoryTest = new MemberRepository();

    @Test
    @DisplayName("Is my member registered?")
    void registerMember() {
        //GIVEN
        Member adminMain = new Member("0", "", "pieter.pauwels13@gmail.com", "", "", "", "", "", "", "XXX");
        //WHEN
        HashMap<String, Member> membersByIdTest = new HashMap<>();
        repositoryTest.registerMember(adminMain);
        repositoryTest.
        //THEN
        Assertions.assertThat(membersByIdTest.size()).isEqualTo(1);


    }
}