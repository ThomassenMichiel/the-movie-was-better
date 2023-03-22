package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        Book harryPotter = new Book("978-0-7475-3269-9", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0-7475-3849-2", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0-7475-4215-5", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        List<Book> answer = bookRepository.getAllBooks();

        assertThat(answer).hasSize(3);
        assertThat(answer).containsExactlyInAnyOrder(harryPotter, harryPotter2, harryPotter3);
    }

    @Test
    @DisplayName("Find book by ISBN")
    void findByIsbn() {
        Book harryPotter = new Book("978-0-7475-3269-9", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0-7475-3849-2", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0-7475-4215-5", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        Book answer = bookRepository.findByIsbn("0-7475-3849-2");

        assertThat(answer).isEqualTo(harryPotter2);
    }
}
