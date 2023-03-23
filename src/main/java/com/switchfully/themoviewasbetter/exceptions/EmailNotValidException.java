package com.switchfully.themoviewasbetter.exceptions;

public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException() {
        super("Given email is not valid.");
    }
}
