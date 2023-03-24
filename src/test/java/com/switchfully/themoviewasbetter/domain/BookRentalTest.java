package com.switchfully.themoviewasbetter.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookRentalTest {

    @Test
    @DisplayName("equals")

    void testEquals() {
        Member member00 = new Member();
        LocalDate date =  LocalDate.now();
        Book book = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");

        BookRental bookRental1 = new BookRental("1234567890123", member00, book, date);

        BookRental bookRental2 = new BookRental("1234567890123", member00, book, date);

        assertThat(bookRental1).isEqualTo(bookRental2);
    }
    @Test
    @DisplayName("hashCode")
    void testHashCode() {
        Member member00 = new Member();
        LocalDate date =  LocalDate.now();
        Book book = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");

        BookRental bookRental1 = new BookRental("1234567890123", member00, book, date);
        BookRental bookRental2 = new BookRental("1234567890123", member00, book, date);

        assertThat(bookRental1).hasSameHashCodeAs(bookRental2);
    }
}