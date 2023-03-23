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
    }

    private static Member putAdminMain() {
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "Pauwels", "",
                "", "", "", "Gent", "XXX");
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
