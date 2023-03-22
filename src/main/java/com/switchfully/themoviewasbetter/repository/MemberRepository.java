package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.security.Role;
import org.springframework.stereotype.Repository;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {

    private final HashMap<String, Member> membersById = new HashMap<>();

    public MemberRepository() {
        this.membersById.put("0", putAdminMain());
    }

    private static Member putAdminMain() {
        Member adminMain = new Member("0", "123", "pieter.pauwels13@gmail.com", "", "",
                "", "", "", "", "XXX");
        adminMain.setRole(Role.ADMIN);
        return adminMain;
    }

    public Collection<Member> getAllUsers() {
        return membersById.values();
    }

    public Member registerMember(Member user) {
        membersById.put(user.getId(), user);
        return user;

    }

    public Member registerAdministrator(Member user) {
        throw new UnsupportedOperationException();
    }

    public Member registerLibrarian(Member user) {
        throw new UnsupportedOperationException();
    }


    public Member getMember(String id) {
        return membersById.get(id);
    }


}
