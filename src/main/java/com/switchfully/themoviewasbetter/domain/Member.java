package com.switchfully.themoviewasbetter.domain;

import com.switchfully.themoviewasbetter.exceptions.EmailNotValidException;
import com.switchfully.themoviewasbetter.exceptions.FieldIsEmptyException;
import com.switchfully.themoviewasbetter.security.Feature;
import com.switchfully.themoviewasbetter.security.Role;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Member {

    private String inss; //unique
    private String email; // unique + x.x@.x reg ex
    private String lastname; //not null
    private String firstname;
    private String streetName;
    private String number;
    private String postcode;
    private String city; // not null
    private String password;
    private Role role;
    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("\\b[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z0-9]{2,}\\b");

    public Member() {
    }

    public Member(String inss, String email, String lastname, String firstname, String streetName,
                  String number, String postcode, String city, String password) {
        this(inss, email,lastname, firstname, streetName, number, postcode, city, password, Role.MEMBER);
    }

    public Member(String inss, String email, String lastname, String firstname, String streetName,
                  String number, String postcode, String city, String password, Role role) {
        checkInss(inss);
        checkEmail(email);
        checkLastname(lastname);
        this.firstname = firstname;
        this.streetName = streetName;
        this.number = number;
        this.postcode = postcode;
        checkCity(city);
        this.password = password;
        this.role = role;
    }

    // Getters

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

    public String getNumber() {
        return number;
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


    public Member setInss(String inss) {
        this.inss = inss;
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

    public Member setNumber(String number) {
        this.number = number;
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
    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public static boolean fieldIsEmpty(String field) {
        return field.trim().isEmpty();
    }


    public final void checkInss(String inss) {
        if (fieldIsEmpty(inss)) {
            throw new FieldIsEmptyException("INSS");
        }
        this.inss = inss;
    }

    public final void checkEmail(String email) {
        if (!isValidEmail(email)) {
            throw new EmailNotValidException();
        }
        this.email = email;
    }

    public final void checkLastname(String lastname) {
        if (fieldIsEmpty(lastname)) {
            throw new FieldIsEmptyException("lastName");
        }
        this.lastname = lastname;
    }

    public final void checkCity(String city) {
        if (fieldIsEmpty(city)) {
            throw new FieldIsEmptyException("city");
        }
        this.city = city;
    }

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
        return Objects.equals(inss, user.inss) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inss, email);
    }


}
