package com.switchfully.themoviewasbetter.domain;

import com.switchfully.themoviewasbetter.exceptions.EmailNotValidException;
import com.switchfully.themoviewasbetter.exceptions.FieldIsEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Nested
    @DisplayName("Exception")
    class Exception {
        @Test
        @DisplayName("Invalid email")
        void email_isInvalid() {
            EmailNotValidException emailNotValidException = assertThrows(EmailNotValidException.class, () ->
                    new Member("123", "gwegasgd"
                            , "Van Gastel", "Sven", "molenstraat",
                            "28", "2920", "Kalmthout", "passwoordTest"));
            assertEquals("Given email is not valid.", emailNotValidException.getMessage());
        }
        @Test
        @DisplayName("Invalid last name")
        void lastname_isInvalid() {
            FieldIsEmptyException fieldIsEmptyException = assertThrows(FieldIsEmptyException.class, () ->
                    new Member("123", "sven@gmail.com"
                            , "   ", "Sven", "molenstraat",
                            "28", "2920", "Kalmthout", "passwoordTest"));
            assertEquals("Field lastName can't be empty.", fieldIsEmptyException.getMessage());
        }

        @Test
        @DisplayName("Invalid city")
        void city_IsInvalid() {
            FieldIsEmptyException fieldIsEmptyException = assertThrows(FieldIsEmptyException.class, () ->
                    new Member("123", "sven@gmail.com"
                            , "Van Gastel", "Sven", "molenstraat",
                            "28", "2920", "   ", "passwoordTest"));
            assertEquals("Field city can't be empty.", fieldIsEmptyException.getMessage());
        }

        @Test
        @DisplayName("Invalid INSS")
        void inss_isInvalid() {
            FieldIsEmptyException fieldIsEmptyException = assertThrows(FieldIsEmptyException.class, () ->
                    new Member("", "sven@gmail.com"
                            , "Van Gast", "erik", "molenbaan",
                            "3", "2000", "Antwerrpen", "passwoordTest"));
            assertEquals("Field INSS can't be empty.", fieldIsEmptyException.getMessage());
        }
    }

    @Test
    @DisplayName("Create a new member")
    void createMember() {
        assertDoesNotThrow(() -> new Member("123456", "sven@gmail.com"
                , "Van Gast", "erik", "molenbaan",
                "3", "2000", "Antwerrpen", "passwoordTest"));
    }

}
