package com.switchfully.themoviewasbetter.dto;

public class RentedBookDTO {
    private final String rentingMember;

    public String getRentingMember() {
        return rentingMember;
    }

    public RentedBookDTO(String rentingMember) {
        this.rentingMember = rentingMember;
    }
}
