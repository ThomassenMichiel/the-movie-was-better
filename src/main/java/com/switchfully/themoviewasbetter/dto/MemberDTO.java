package com.switchfully.themoviewasbetter.dto;

import com.switchfully.themoviewasbetter.security.Role;

public class MemberDTO {

    private String id;
    private String INSS;
    private String email;
    private String lastname;
    private String firstname;
    private String streetName;
    private String nr;
    private String postcode;
    private String city; // not null
    private String password;
    private Role role;

    public String getId(){
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
    public MemberDTO setId(String id) {
        this.id = id;
        return this;
    }

    public MemberDTO setINSS(String INSS) {
        this.INSS = INSS;
        return this;
    }

    public MemberDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public MemberDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public MemberDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public MemberDTO setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public MemberDTO setNr(String nr) {
        this.nr = nr;
        return this;
    }

    public MemberDTO setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public MemberDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public MemberDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public MemberDTO setRole(Role role) {
        this.role = role;
        return this;
    }
}
