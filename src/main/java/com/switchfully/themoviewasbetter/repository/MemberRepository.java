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
        Member member = new Member("124", "sven@gmail.com"
                , "Van Gastel", "Sven", "molenstraat",
                "28", "2920", "Kalmthout", "passwoordTest");
        repository.put(member.getEmail(), member);
    }

    private static Member putAdminMain() {
        Member admin = new Member("123", "pieter.pauwels13@gmail.com", "Pauwels", "",
                "", "", "", "Gent", "XXX"); // cGlldGVyLnBhdXdlbHMxM0BnbWFpbC5jb206WFhY
        admin.setRole(Role.ADMIN);
        return admin;
    }

    public Collection<Member> findAll() {
        return repository.values();
    }

    public Member create(Member member) {
        checkIfUserInssAndEmailAreUnique(member);

        repository.put(member.getEmail(), member);
        return member;

    }


    public Member findByEmail(String email) {
        return repository.get(email);
    }


    private void checkIfUserInssAndEmailAreUnique(Member newMember) {
        if (this.repository.values().stream().map(Member::getEmail).anyMatch(email -> email.equals(newMember.getEmail()))
                || this.repository.values().stream().map(Member::getInss).anyMatch(inss -> inss.equals(newMember.getInss()))) {
            throw new MemberNotUniqueException();
        }
    }

}
