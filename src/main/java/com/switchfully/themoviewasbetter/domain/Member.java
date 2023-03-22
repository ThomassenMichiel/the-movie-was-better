package com.switchfully.themoviewasbetter.domain;

import com.switchfully.themoviewasbetter.dto.MemberDTO;
import com.switchfully.themoviewasbetter.security.Feature;
import com.switchfully.themoviewasbetter.security.Role;

import java.util.Objects;

public class Member {

    private String id;
    private String INSS; //unique
    private String email; // unique
    private String lastname; //not null
    private String firstname;
    private String streetName;
    private String nr;
    private String postcode;
    private String city; // not null
    private String password;
    private Role role;


    public Member(){}
    public Member(String id, String INSS, String email, String lastname, String firstname, String streetName,
                  String nr, String postcode, String city, String password) {
        this.id = id;
        this.INSS = INSS;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.streetName = streetName;
        this.nr = nr;
        this.postcode = postcode;
        this.city = city;
        this.password = password;
        this.role = Role.MEMBER;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getINSS() {
        return INSS;
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

    // Setters

    public Member setId(String id) {
        this.id = id;
        return this;
    }

    public Member setINSS(String INSS) {
        this.INSS = INSS;
        return this;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public Member setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public Member setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public Member setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public Member setNr(String nr) {
        this.nr = nr;
        return this;
    }

    public Member setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public Member setCity(String city) {
        this.city = city;
        return this;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public Member setRole(Role role) {
        this.role = role;
        return this;
    }

    // Methods
    public boolean doesPasswordMatch(String password) {
        return this.password.equals(password);
    }

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member user = (Member) o;
        return Objects.equals(INSS, user.INSS) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(INSS, email);
    }


}
