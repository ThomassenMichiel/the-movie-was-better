package com.switchfully.themoviewasbetter.dto;

import com.switchfully.themoviewasbetter.security.Role;

public class CreateMemberDTO {
    private final String inss;
    private final String email;
    private final String lastname;
    private final String firstname;
    private final String streetName;
    private final String nr;
    private final String postcode;
    private final String city; // not null
    private final String password;
    private final Role role;

    public CreateMemberDTO(String inss, String email, String lastname, String firstname, String streetName, String nr, String postcode, String city, String password, Role role) {
        this.inss = inss;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.streetName = streetName;
        this.nr = nr;
        this.postcode = postcode;
        this.city = city;
        this.password = password;
        this.role = role;
    }

    public String getInss() {
        return inss;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getNr() {
        return nr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
