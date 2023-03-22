package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.dto.BookDTO;
import com.switchfully.themoviewasbetter.exceptions.BookNotFoundException;
import com.switchfully.themoviewasbetter.mapper.BookMapper;
import com.switchfully.themoviewasbetter.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class BookServiceTest {
    private BookService service;
    private BookRepository repository;
    private BookMapper mapper;

    @BeforeEach
    void setUp() {
        service = new BookService(new BookRepository(), new BookMapper());
    }

    @Test
    @DisplayName("Get all books")
    void getAllBooks() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        List<BookDTO> answer = service.getAllBooks();

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
        BookDTO answer = service.findByIsbn(isbn);

        assertThat(answer).isEqualTo(bookDTO);
    }

    @Test
    @DisplayName("Find by ISBN")
    void findByIsbn_notFound() {
        BookNotFoundException exception = new BookNotFoundException();

        assertThatThrownBy(() -> service.findByIsbn(""))
                .hasMessage(exception.getMessage())
                .isInstanceOf(exception.getClass());
    }

    public static Stream<Arguments> findByIsbn() {
        return Stream.of(
                Arguments.of("9780747532699", new BookDTO("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet")),
                Arguments.of("0747538492", new BookDTO("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened")),
                Arguments.of("0747542155", new BookDTO("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty"))
        );
    }
}
