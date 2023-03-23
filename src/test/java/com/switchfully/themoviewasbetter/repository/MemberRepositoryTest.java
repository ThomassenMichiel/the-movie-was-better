package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.exceptions.MemberNotUniqueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberRepositoryTest {
    private MemberRepository repository = new MemberRepository();
    private HashMap<String, Member> members = new HashMap<>();

    @BeforeEach
    void beforeEach() {
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "pauwels",
                "", "", "", "", "Gent", "XXX");

        var member1 = new Member("1", "124", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");

        var member2 = new Member("2", "125", "sven6@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest");

        members.put(adminMain.getEmail(), adminMain);
        members.put(member1.getEmail(), member1);
        members.put(member2.getEmail(), member2);

        repository.registerMember(member2);
    }

    @Test
    @DisplayName("Is my admin registered?")
    void registerMember() {
        //GIVEN
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "Pauwels",
                "", "", "", "", "Gent", "XXX");

        //WHEN
        //THEN
        Assertions.assertThat(repository.getAllUsers()).contains(adminMain);
    }

    @Test
    void getAllUsersContains2Users() {
        Assertions.assertThat(repository.getAllUsers()).containsExactlyInAnyOrderElementsOf(members.values().stream().toList());
    }

    @Test
    @DisplayName("Is my member registered?")
    void registerNewMember() {
        //GIVEN

        var member1 = new Member("4", "126", "sven2@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");
        var member2 = new Member("5", "127", "sven28447815@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");
        //WHEN
        repository.registerMember(member1);
        //THEN
        Assertions.assertThat(repository.getAllUsers())
                .contains(member1);
        //WHEN
        repository.registerMember(member2);
        //THEN
        Assertions.assertThat(repository.getAllUsers())
                .contains(member2);
    }

    @Test
    void cantRegisterAMemberWithTheSameEmail() {
        var member2 = new Member("2", "125", "sven@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest");
        MemberNotUniqueException memberNotUniqueException = assertThrows(MemberNotUniqueException.class, () ->
                repository.registerMember(member2));
        assertEquals(memberNotUniqueException.getMessage(), "Email address and/or INSS is not unique. " +
                "Member already exists.");
    }

    @Test
    void cantRegisterAMemberWithTheSameINSS() {
        var member2 = new Member("2", "123", "sven2@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest");
        MemberNotUniqueException memberNotUniqueException = assertThrows(MemberNotUniqueException.class, () ->
                repository.registerMember(member2));
        assertEquals(memberNotUniqueException.getMessage(), "Email address and/or INSS is not unique. " +
                "Member already exists.");
    }


}