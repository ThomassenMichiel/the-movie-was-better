package com.switchfully.themoviewasbetter.exceptions;

public class MemberNotUniqueException extends RuntimeException{
    public MemberNotUniqueException() {
        super("Email address and/or INSS is not unique. Member already exists.");
    }
}
