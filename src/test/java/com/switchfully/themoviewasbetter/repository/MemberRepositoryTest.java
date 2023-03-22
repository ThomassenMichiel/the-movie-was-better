package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    private MemberRepository repositoryTest = new MemberRepository();


    @BeforeEach
    void beforeEach(){

    }
    @Test
    @DisplayName("Is my admin registered?")
    void registerMember() {
        //GIVEN
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "", "", "", "", "", "", "XXX");

        //WHEN
        //THEN
        Assertions.assertThat(repositoryTest.getAllUsers().stream().toList()).isEqualTo(List.of(adminMain));
    }

    @Test
    @DisplayName("Is my member registered?")
    void registerNewMember() {
        //GIVEN
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "", "", "", "", "", "", "XXX");

        var member1 = new Member("1", "123", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");

        var member2 = new Member("2", "125", "sven@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen","passwoordTest");
        //WHEN
        repositoryTest.registerMember(member1);
        //THEN
        Assertions.assertThat(repositoryTest.getAllUsers().stream().toList()).isEqualTo(List.of(adminMain, member1));
        //WHEN
        repositoryTest.registerMember(member2);
        //THEN
        Assertions.assertThat(repositoryTest.getAllUsers().stream().toList()).isEqualTo(List.of(adminMain, member1, member2));
    }
}