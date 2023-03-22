package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BookRepositoryTest {
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
    }

    @Test
    @DisplayName("Get all books")
    void getAllBooks() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        List<Book> answer = bookRepository.getAllBooks(new HashMap<>());

        assertThat(answer).hasSize(3);
        assertThat(answer).containsExactlyInAnyOrder(harryPotter, harryPotter2, harryPotter3);
    }

    @Test
    @DisplayName("Find book by ISBN")
    void findByIsbn() {
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");

        Book answer = bookRepository.findByIsbn("0747538492");

        assertThat(answer).isEqualTo(harryPotter2);
    }

    @Test
    @DisplayName("Get all books")
    void getAllBooks_wildCards_withNoSpecialWildcards() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "74753");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(2);
        assertThat(answer).containsExactlyInAnyOrder(harryPotter, harryPotter2);
    }

    @Test
    @DisplayName("Get all books")
    void getAllBooks_wildCards_withActualWildcards() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "07475.");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3);
        assertThat(answer).containsExactlyInAnyOrder(harryPotter, harryPotter2, harryPotter3);
    }

    @Test
    @DisplayName("Get all books")
    void getAllBooks_wildCards_withActualWildcards_doubleDigits() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "69{2}");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(1);
        assertThat(answer).containsExactlyInAnyOrder(harryPotter);
    }
}
