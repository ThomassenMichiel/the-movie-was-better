package com.switchfully.themoviewasbetter.dto;

import com.switchfully.themoviewasbetter.security.Role;

import java.util.Objects;

public class MemberDTO {

    private final String email;
    private final String lastName;
    private final String firstName;
    private final String streetName;
    private final String number;
    private final String postcode;
    private final String city; // not null
    private final Role role;


    public MemberDTO(String email, String lastName, String firstName, String streetName, String number, String postcode, String city, Role role) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.streetName = streetName;
        this.number = number;
        this.postcode = postcode;
        this.city = city;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getNumber() {
        return number;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberDTO memberDTO)) return false;
        return Objects.equals(email, memberDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
