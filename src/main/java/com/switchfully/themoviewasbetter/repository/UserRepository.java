package com.switchfully.themoviewasbetter.repository;

import org.springframework.stereotype.Repository;
import switchfully.themoviewasbetter.domain.User;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> users;


    public UserRepository(List<User> users) {
        this.users = users;
    }

    public List<User> getAllUsers(){
        return users;
    }

    public User registerMember(User user){
        return user;

    }

    public User registerAdministrator(User user){

    }

    public User registerLibrarian(User user){

    }




}
