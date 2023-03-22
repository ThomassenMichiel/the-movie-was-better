package com.switchfully.themoviewasbetter.security;

public class UsernamePassword {
    private final String userId;
    private final String password;

    public UsernamePassword(String userId, String password) {

        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
