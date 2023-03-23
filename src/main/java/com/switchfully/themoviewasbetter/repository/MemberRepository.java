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

    public Member registerMember(Member newMember) {
        if(checkIfUserInssAndEmailAreUnique(newMember)) {
            throw new MemberNotUniqueException();
        }
        repository.put(newMember.getEmail(), newMember);
        return newMember;

    }

    public Member registerAdministrator(Member user) {
        throw new UnsupportedOperationException();
    }

    public Member registerLibrarian(Member user) {
        throw new UnsupportedOperationException();
    }


    public Member getMember(String id) {
        return repository.get(id);
    }


    private boolean checkIfUserInssAndEmailAreUnique(Member newMember) {
        return this.repository.values().stream().map(Member::getEmail).anyMatch(email -> email.equals(newMember.getEmail()))
                || this.repository.values().stream().map(Member::getInss).anyMatch(inss -> inss.equals(newMember.getInss()));
    }

}
