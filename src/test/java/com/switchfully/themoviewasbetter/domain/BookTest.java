package com.switchfully.themoviewasbetter.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BookTest {

    @Test
    @DisplayName("equals")
    void testEquals() {
        Book book = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");
        Book book2 = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");

        assertThat(book).isEqualTo(book2);
    }

    @Test
    @DisplayName("hashCode")
    void testHashCode() {
        Book book = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");
        Book book2 = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");

        assertThat(book).hasSameHashCodeAs(book2);
    }

    @Test
    @DisplayName("toString")
    void testToString() {
        Book book = new Book("1234567890123", "A book", "My", "Name", "dis b ma buk");
        assertThat(book.toString()).hasToString("Book{isbn='1234567890123', title='A book', authorFirstName='My', authorLastName='Name', smallSummary='dis b ma buk'}");
    }
}
