package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.dto.BookDTO;
import com.switchfully.themoviewasbetter.mapper.BookMapper;
import com.switchfully.themoviewasbetter.repository.BookRepository;
import com.switchfully.themoviewasbetter.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BookControllerTest {
    private BookController controller;
    private BookService service;

    @BeforeEach
    void setUp() {
        service = new BookService(new BookRepository(), new BookMapper());
        controller = new BookController(service);
    }

    @Test
    @DisplayName("Get all books")
    void getAllBooks() {
        Book harryPotter = new Book("978-0-7475-3269-9", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0-7475-3849-2", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0-7475-4215-5", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        List<BookDTO> answer = controller.getAllBooks();

        assertThat(answer).hasSize(3);
        assertThat(answer).extracting(BookDTO::getIsbn).contains(harryPotter.getIsbn(), harryPotter2.getIsbn(), harryPotter3.getIsbn());
        assertThat(answer).extracting(BookDTO::getTitle).contains(harryPotter.getTitle(), harryPotter2.getTitle(), harryPotter3.getTitle());
        assertThat(answer).extracting(BookDTO::getAuthorFirstName).contains(harryPotter.getAuthorFirstName(), harryPotter2.getAuthorFirstName(), harryPotter3.getAuthorFirstName());
        assertThat(answer).extracting(BookDTO::getAuthorLastName).contains(harryPotter.getAuthorLastName(), harryPotter2.getAuthorLastName(), harryPotter3.getAuthorLastName());
        assertThat(answer).extracting(BookDTO::getSmallSummary).contains(harryPotter.getSmallSummary(), harryPotter2.getSmallSummary(), harryPotter3.getSmallSummary());
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Find by ISBN")
    void findByIsbn(String isbn, BookDTO bookDTO) {
        BookDTO answer = controller.getBookDetails(isbn);

        assertThat(answer).isEqualTo(bookDTO);
    }

    public static Stream<Arguments> findByIsbn() {
        return Stream.of(
                Arguments.of("978-0-7475-3269-9", new BookDTO("978-0-7475-3269-9", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet")),
                Arguments.of("0-7475-3849-2", new BookDTO("0-7475-3849-2", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened")),
                Arguments.of("0-7475-4215-5", new BookDTO("0-7475-4215-5", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty"))
        );
    }
}
