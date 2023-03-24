package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.exceptions.MemberNotUniqueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.switchfully.themoviewasbetter.security.Role.ADMIN;
import static com.switchfully.themoviewasbetter.security.Role.LIBRARIAN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Member repository test")
class MemberRepositoryTest {
    private final HashMap<String, Member> members = new HashMap<>();
    private MemberRepository repository;

    @BeforeEach
    void beforeEach() {
        repository = new MemberRepository();
        Member adminMain = new Member("123", "pieter.pauwels13@gmail.com", "pauwels",
                "", "", "", "", "Gent", "XXX");

        var member1 = new Member("124", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");

        var member2 = new Member("125", "sven6@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest");

        members.put(adminMain.getEmail(), adminMain);
        members.put(member1.getEmail(), member1);
        members.put(member2.getEmail(), member2);

        repository.create(member2);
    }

    @Test
    void saveLibrarian() {
        Member librarian = new Member("12399999", "pieter.pauwels13999999@gmail.com", "Pauwels",
                "", "", "", "", "Gent", "XXX", LIBRARIAN);
        repository.create(librarian);
        assertThat(repository.findAll()).contains(librarian);

        Member member = repository.findById(librarian.getEmail());
        assertThat(member).extracting("role").isEqualTo(LIBRARIAN);
    }

    @Nested
    @DisplayName("Find all users")
    class FindAll {
        @Test
        @DisplayName("Find all users")
        void getAllUsers_contains2Users() {
            assertThat(repository.findAll()).containsExactlyInAnyOrderElementsOf(members.values().stream().toList());
        }
    }

    @Nested
    @DisplayName("Creation of members")
    class Creation {
        @Test
        @DisplayName("Create a new member")
        void registerMember() {
            Member adminMain = new Member("123", "pieter.pauwels13@gmail.com", "Pauwels",
                    "", "", "", "", "Gent", "XXX");
            assertThat(repository.findAll()).contains(adminMain);
        }

        @Test
        @DisplayName("Create multiple members")
        void registerNewMember() {
            Member member1 = new Member("126", "sven2@gmail.com"
                    , "Van Gastel", "Sven", "molenstraat",
                    "28", "2920", "Kalmthout", "passwoordTest");
            Member member2 = new Member("127", "sven28447815@gmail.com"
                    , "Van Gastel", "Sven", "molenstraat",
                    "28", "2920", "Kalmthout", "passwoordTest");

            repository.create(member1);
            assertThat(repository.findAll())
                    .contains(member1);
            repository.create(member2);
            assertThat(repository.findAll())
                    .contains(member2);
        }

        @Test
        @DisplayName("Create an admin")
        void saveAdmin() {
            Member adminMain = new Member("12399999", "pieter.pauwels13999999@gmail.com", "Pauwels",
                    "", "", "", "", "Gent", "XXX", ADMIN);
            Member member = repository.create(adminMain);
            assertThat(repository.findAll()).contains(adminMain);

            assertThat(member).extracting("role").isEqualTo(ADMIN);
        }

        @Nested
        @DisplayName("Creation errors")
        class CreationErrors {
            @Test
            @DisplayName("Cannot create a member with an existing email")
            void cantRegisterAMemberWithTheSameEmail() {
                Member member2 = new Member("125", "sven@gmail.com"
                        , "Van Gast", "erik", "molenbaan",
                        "3", "2000", "Antwerrpen", "passwoordTest");
                MemberNotUniqueException memberNotUniqueException = assertThrows(MemberNotUniqueException.class, () ->
                        repository.create(member2));
                assertEquals(memberNotUniqueException.getMessage(), "Email address and/or INSS is not unique. " +
                        "Member already exists.");
            }

            @Test
            @DisplayName("Cannot create a member with an existing INSS")
            void cantRegisterAMemberWithTheSameINSS() {
                Member member2 = new Member("123", "sven2@gmail.com"
                        , "Van Gast", "erik", "molenbaan",
                        "3", "2000", "Antwerrpen", "passwoordTest");
                MemberNotUniqueException memberNotUniqueException = assertThrows(MemberNotUniqueException.class, () ->
                        repository.create(member2));
                assertEquals(memberNotUniqueException.getMessage(), "Email address and/or INSS is not unique. " +
                        "Member already exists.");
            }
        }


    }


}
