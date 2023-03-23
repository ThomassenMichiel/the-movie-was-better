package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.exceptions.MemberNotUniqueException;
import com.switchfully.themoviewasbetter.security.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class MemberRepository {

    private final HashMap<String, Member> repository = new HashMap<>();

    public MemberRepository() {
        this.repository.put("pieter.pauwels13@gmail.com", putAdminMain());
        var member1 = new Member("1", "124", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");
        repository.put(member1.getEmail(), member1);
        var member2 = new Member("2", "125", "sven6@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest");
        repository.put(member2.getEmail(), member2);
    }

    private static Member putAdminMain() {
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "Pauwels", "",
                "", "", "", "Gent", "XXX"); // cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206WFhY
        adminMain.setRole(Role.ADMIN);
        return adminMain;
    }

    public Collection<Member> getAllUsers() {
        return repository.values();
    }

    public Member registerMember(Member member) {
        checkIfUserInssAndEmailAreUnique(member);

        repository.put(member.getEmail(), member);
        return member;

    }

    public Member registerAdministrator(Member member) {
        checkIfUserInssAndEmailAreUnique(member);

        member.setRole(Role.ADMIN);
        repository.put(member.getEmail(), member);
        return member;
    }

    public Member registerLibrarian(Member member) {
        throw new UnsupportedOperationException();
    }


    public Member getMember(String id) {
        return repository.get(id);
    }


    private void checkIfUserInssAndEmailAreUnique(Member newMember) {
        if (this.repository.values().stream().map(Member::getEmail).anyMatch(email -> email.equals(newMember.getEmail()))
                || this.repository.values().stream().map(Member::getInss).anyMatch(inss -> inss.equals(newMember.getInss()))) {
            throw new MemberNotUniqueException();
        }
    }

}
