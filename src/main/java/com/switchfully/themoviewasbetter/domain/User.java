package switchfully.themoviewasbetter.domain;

import java.util.Objects;

public class User {

    private String id;
    private String INSS; //unique
    private String email; // unique
    private String lastname; //not null
    private String firstname;
    private String streetName;
    private String nr;
    private String postcode;
    private String city; // not null


    public User(String id, String INSS, String email, String lastname, String firstname, String streetName, String nr, String postcode, String city) {
        this.id = id;
        this.INSS = INSS;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.streetName = streetName;
        this.nr = nr;
        this.postcode = postcode;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getINSS() {
        return INSS;
    }

    public void setINSS(String INSS) {
        this.INSS = INSS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(INSS, user.INSS) && Objects.equals(email, user.email);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(INSS, email);
//    }
}
