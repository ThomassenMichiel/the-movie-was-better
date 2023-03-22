package com.switchfully.themoviewasbetter.domain;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setINSS(String INSS) {
        this.INSS = INSS;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
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
