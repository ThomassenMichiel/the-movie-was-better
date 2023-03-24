package com.switchfully.themoviewasbetter.dto;

public class ReturnedBookRentalDTO {
    private final String message;

    public ReturnedBookRentalDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
