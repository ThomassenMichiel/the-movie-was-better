package com.switchfully.themoviewasbetter.exceptions;

public class FieldIsEmptyException extends RuntimeException{
    public FieldIsEmptyException(String message) {
        super(String.format("Field %s can't be empty.", message));
    }
}
