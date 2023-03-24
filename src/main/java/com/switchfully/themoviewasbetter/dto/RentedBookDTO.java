package com.switchfully.themoviewasbetter.dto;

import java.util.Objects;

public class RentedBookDTO {
    private final String rentingMember;

    public String getRentingMember() {

        return rentingMember;
    }

    public RentedBookDTO(String rentingMember) {
        this.rentingMember = rentingMember;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentedBookDTO that = (RentedBookDTO) o;
        return Objects.equals(rentingMember, that.rentingMember);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rentingMember);
    }
}
