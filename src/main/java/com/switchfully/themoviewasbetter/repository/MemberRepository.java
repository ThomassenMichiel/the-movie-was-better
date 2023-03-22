package com.switchfully.themoviewasbetter.repository;
import switchfully.themoviewasbetter.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {

    private final HashMap<String, Member> membersById;


    public MemberRepository() {
        this.membersById = new HashMap<>();
    }

    public Collection<Member> getAllUsers() {
        return membersById.values();
    }

    public Member registerMember(switchfully.themoviewasbetter.domain.Member user) {
        return user;

    }

    public Member registerAdministrator(switchfully.themoviewasbetter.domain.Member user) {
        throw new UnsupportedOperationException();
    }

    public switchfully.themoviewasbetter.domain.Member registerLibrarian(switchfully.themoviewasbetter.domain.Member user) {
        throw new UnsupportedOperationException();
    }


    public Member getMember(String id) {
        return membersById.get(id);
    }
}
