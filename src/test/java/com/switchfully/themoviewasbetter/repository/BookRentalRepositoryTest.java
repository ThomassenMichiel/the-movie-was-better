package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;

class BookRentalRepositoryTest {
    private BookRentalRepository repository;

    @BeforeEach
    void setUp() {
        repository = new BookRentalRepository();
    }

    @Test
    @DisplayName("Find all")
    void findAll() {
        BookRental aBook = new BookRental("0", new Member(), new Book("a book", "", "", "", ""), LocalDate.of(1990,10,10));

        assertThat(repository.findAll(new HashMap<>())).contains(aBook);
    }

    @Test
    @DisplayName("Create a BookRental")
    void create() {
        BookRental aBook = new BookRental("0", new Member(), new Book("a book", "", "", "", ""), LocalDate.of(1990,10,10));
        BookRental notMaBook = new BookRental("1", new Member(), new Book("not ma book", "", "", "", ""), LocalDate.of(1990,10,10));

        repository.create(notMaBook);

        assertThat(repository.findAll(new HashMap<>())).contains(aBook, notMaBook);
    }

    @Test
    @DisplayName("Delete")
    void delete() {
        BookRental aBook = new BookRental("0", new Member(), new Book("a book", "", "", "", ""), LocalDate.of(1990,10,10));
        repository.delete(aBook.getId());
        assertThat(repository.findAll(new HashMap<>())).isEmpty();
    }
}
