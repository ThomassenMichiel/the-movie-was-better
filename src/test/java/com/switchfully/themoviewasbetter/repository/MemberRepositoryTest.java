package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.exceptions.MemberNotUniqueException;
import com.switchfully.themoviewasbetter.security.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Member repository test")
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

    @Nested
    @DisplayName("Find all users")
    class FindAll {
        @Test
        @DisplayName("Find all users")
        void getAllUsers_contains2Users() {
            assertThat(repository.getAllUsers()).containsExactlyInAnyOrderElementsOf(members.values().stream().toList());
        }
    }

    @Nested
    @DisplayName("Creation of members")
    class Creation {
        @Test
        @DisplayName("Create a new member")
        void registerMember() {
            Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "Pauwels",
                    "", "", "", "", "Gent", "XXX");
            assertThat(repository.getAllUsers()).contains(adminMain);
        }

        @Test
        @DisplayName("Create multiple members")
        void registerNewMember() {
            Member member1 = new Member("4", "126", "sven2@gmail.com"
                    , "Van Gastel", "Sven", "molenstraat",
                    "28", "2920", "Kalmthout", "passwoordTest");
            Member member2 = new Member("5", "127", "sven28447815@gmail.com"
                    , "Van Gastel", "Sven", "molenstraat",
                    "28", "2920", "Kalmthout", "passwoordTest");

            repository.registerMember(member1);
            assertThat(repository.getAllUsers())
                    .contains(member1);
            repository.registerMember(member2);
            assertThat(repository.getAllUsers())
                    .contains(member2);
        }

        @Test
        @DisplayName("Create an admin")
        void saveAdmin() {
            Member adminMain = new Member("9999", "12399999", "pieter.pauwels13999999@gmail.com", "Pauwels",
                    "", "", "", "", "Gent", "XXX");
            repository.registerAdministrator(adminMain);
            assertThat(repository.getAllUsers()).contains(adminMain);

            Member member = repository.getMember(adminMain.getEmail());
            assertThat(member).extracting("role").isEqualTo(Role.ADMIN);
        }

        @Nested
        @DisplayName("Creation errors")
        class CreationErrors {
            @Test
            @DisplayName("Cannot create a member with an existing email")
            void cantRegisterAMemberWithTheSameEmail() {
                Member member2 = new Member("2", "125", "sven@gmail.com"
                        , "Van Gast", "erik", "molenbaan",
                        "3", "2000", "Antwerrpen", "passwoordTest");
                MemberNotUniqueException memberNotUniqueException = assertThrows(MemberNotUniqueException.class, () ->
                        repository.registerMember(member2));
                assertEquals(memberNotUniqueException.getMessage(), "Email address and/or INSS is not unique. " +
                        "Member already exists.");
            }

            @Test
            @DisplayName("Cannot create a member with an existing INSS")
            void cantRegisterAMemberWithTheSameINSS() {
                Member member2 = new Member("2", "123", "sven2@gmail.com"
                        , "Van Gast", "erik", "molenbaan",
                        "3", "2000", "Antwerrpen", "passwoordTest");
                MemberNotUniqueException memberNotUniqueException = assertThrows(MemberNotUniqueException.class, () ->
                        repository.registerMember(member2));
                assertEquals(memberNotUniqueException.getMessage(), "Email address and/or INSS is not unique. " +
                        "Member already exists.");
            }
        }


    }


}