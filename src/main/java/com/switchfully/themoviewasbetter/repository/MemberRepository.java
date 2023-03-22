package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
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
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "", "",
                "", "", "", "", "XXX");
        adminMain.setRole(Role.ADMIN);
        return adminMain;
    }

    public Collection<Member> getAllUsers() {
        return repository.values();
    }

    public Member registerMember(Member user) {
        repository.put(user.getId(), user);
        return user;

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


}
