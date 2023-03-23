package com.switchfully.themoviewasbetter.domain;

import com.switchfully.themoviewasbetter.exceptions.EmailNotValidException;
import com.switchfully.themoviewasbetter.exceptions.FieldIsEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void emailOfMemberIsValid() {
        EmailNotValidException emailNotValidException = assertThrows(EmailNotValidException.class, () ->
                new Member("1", "123", "gwegasgd"
                        , "Van Gastel", "Sven", "molenstraat",
                        "28", "2920", "Kalmthout", "passwoordTest"));
        assertEquals(emailNotValidException.getMessage(), "Given email is not valid.");
    }

    @Test
    void lastnameOfMemberIsValid() {
        FieldIsEmptyException fieldIsEmptyException = assertThrows(FieldIsEmptyException.class, () ->
                new Member("1", "123", "sven@gmail.com"
                        , "   ", "Sven", "molenstraat",
                        "28", "2920", "Kalmthout", "passwoordTest"));
        assertEquals(fieldIsEmptyException.getMessage(), "Field lastname can't be empty.");
    }

    @Test
    void cityOfMemberIsValid() {
        FieldIsEmptyException fieldIsEmptyException = assertThrows(FieldIsEmptyException.class, () ->
                new Member("1", "123", "sven@gmail.com"
                        , "Van Gastel", "Sven", "molenstraat",
                        "28", "2920", "   ", "passwoordTest"));
        assertEquals(fieldIsEmptyException.getMessage(), "Field city can't be empty.");
    }

    @Test
    void cantCreateAMemberWithAnEmptyEmail() {
        EmailNotValidException emailNotValidException = assertThrows(EmailNotValidException.class, () ->
                new Member("2", "12355555555", ""
                        , "Van Gast", "erik", "molenbaan",
                        "3", "2000", "Antwerrpen", "passwoordTest"));
        assertEquals(emailNotValidException.getMessage(),"Given email is not valid.");
    }

    @Test
    void cantCreateAMemberWithAnEmptyInss() {
        FieldIsEmptyException fieldIsEmptyException = assertThrows(FieldIsEmptyException.class, () ->
                new Member("2", "", "sven@gmail.com"
                        , "Van Gast", "erik", "molenbaan",
                        "3", "2000", "Antwerrpen", "passwoordTest"));
        assertEquals(fieldIsEmptyException.getMessage(),"Field INSS can't be empty.");
    }

}