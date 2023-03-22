package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.exceptions.InvalidISBNException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
        Book fellowShipOfTheRing = new Book("9780345339706", "Lord of the Rings: Fellowship of the Ring", "J.R.R.", "Tolkien", "they're taking hobbits to isengard");
        Book theComingStorm = new Book("9781423100188", "The Coming Storm (Pirates of the Caribbean: Jack Sparrow, No. 1)", "Rob", "Kidd", "I've got a jar of dirt");

        List<Book> answer = bookRepository.getAllBooks(new HashMap<>());

        assertThat(answer).hasSize(5).containsExactlyInAnyOrder(harryPotter, harryPotter2, harryPotter3, fellowShipOfTheRing, theComingStorm);
    }

    @Test
    @DisplayName("Find book by ISBN")
    void findByIsbn() {
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");

        Optional<Book> answer = bookRepository.findByIsbn("0747538492");

        assertThat(answer).contains(harryPotter2);
    }


    @Test
    @DisplayName("Get all books - wildcards - with no special wildcards")
    void getAllBooks_wildCards_withNoSpecialWildcards() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "74753");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(2).containsExactlyInAnyOrder(harryPotter, harryPotter2);
    }

    @Test
    @DisplayName("Get all books - wildcards - with wildcards")
    void getAllBooks_wildCards_withActualWildcards() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "07475.");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter, harryPotter2, harryPotter3);
    }

    @Test
    @DisplayName("Get all books - with wildcards - double digits")
    void getAllBooks_wildCards_withActualWildcards_doubleDigits() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "69{2}");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(1).containsExactlyInAnyOrder(harryPotter);
    }

    @Test
    @DisplayName("Get all books - with wildcards - valid isbn length")
    void getAllBooks_wildCards_withActualWildcards_validLength13() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "9780747532699");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(1).containsExactlyInAnyOrder(harryPotter);
    }

    @Test
    @DisplayName("Get all books - exception - invalid wildcard because of letters")
    void getAllBooks_wildCards_withActualWildcards_invalidWildcard() {

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "aaaaaa");

        assertThatThrownBy(() -> bookRepository.getAllBooks(params))
                .isInstanceOf(InvalidISBNException.class);

    }

    @Test
    @DisplayName("Get all books - exception - invalid wildcard because of length greater than 13")
    void getAllBooks_wildCards_withActualWildcards_invalidWildcard_lengthGreaterThan13() {

        HashMap<String, String> params = new HashMap<>();
        params.put("isbn", "01234567890123456789");

        assertThatThrownBy(() -> bookRepository.getAllBooks(params))
                .isInstanceOf(InvalidISBNException.class);

    }

    @Test
    @DisplayName("Get all books - by title - with wildcards")
    void getAllBooks_byTitle_withWildcards() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban", "J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("title", "Harry Potter and the P");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(2).containsExactlyInAnyOrder(harryPotter, harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by title - check nonexistent book")
    void getAllBooks_byTitle_nonexistentBook() {
        HashMap<String, String> params = new HashMap<>();
        params.put("title", "Crime and Punishment");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).isEmpty();
    }

    @Test
    @DisplayName("Get all books - by title - case insensitive")
    void getAllBooks_byTitle_caseInsensitive() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("title", "HaRrY pOtTeR aNd ThE");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).containsExactlyInAnyOrder(harryPotter, harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - first name")
    void getAllBooks_byAuthor_firstName() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstName", "J.K.");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - first name - wildcard")
    void getAllBooks_byAuthor_firstName_wildcard() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstName", "K.");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - first name - non existent first name")
    void getAllBooks_byAuthor_firstName_nonexistentFirstName() {

        HashMap<String, String> params = new HashMap<>();
        params.put("firstName", "Fyodor");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).isEmpty();
    }

    @Test
    @DisplayName("Get all books - by author - first name - case insensitive")
    void getAllBooks_byAuthor_firstname_caseInsensitiveLastName() {

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstName", "j.k.");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - last name")
    void getAllBooks_byAuthor_lastname() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("lastName", "Rowling");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - last name - wildcard")
    void getAllBooks_byAuthor_lastname_wildcard() {
        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("lastName", "ling");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - last name - non existent first name")
    void getAllBooks_byAuthor_lastname_nonexistentLastname() {

        HashMap<String, String> params = new HashMap<>();
        params.put("lastName", "Dostoyevsky");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).isEmpty();
    }

    @Test
    @DisplayName("Get all books - by author - last name - case insensitive")
    void getAllBooks_byAuthor_lastname_caseInsensitiveLastName() {

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("lastName", "ROwLiNG");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - first and last name")
    void getAllBooks_byAuthor_firstAndLastName() {

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstname", "j.k.");
        params.put("lastName", "ROwLiNG");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - first and last name - empty first name")
    void getAllBooks_byAuthor_firstAndLastName_emptyFirstName() {

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstname", "");
        params.put("lastName", "ROwLiNG");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(3).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3);
    }

    @Test
    @DisplayName("Get all books - by author - first and last name - empty last name")
    void getAllBooks_byAuthor_firstAndLastName_emptyLastName() {

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");
        Book fellowShipOfTheRing = new Book("9780345339706", "Lord of the Rings: Fellowship of the Ring", "J.R.R.", "Tolkien", "they're taking hobbits to isengard");
        Book theComingStorm = new Book("9781423100188", "The Coming Storm (Pirates of the Caribbean: Jack Sparrow, No. 1)", "Rob", "Kidd", "I've got a jar of dirt");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstname", "j.k.");
        params.put("lastName", "");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(5).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3, fellowShipOfTheRing, theComingStorm);
    }

    @Test
    @DisplayName("Get all books - by author - first and last name - null first name")
    void getAllBooks_byAuthor_firstAndLastName_nullFirstName() {

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");
        Book fellowShipOfTheRing = new Book("9780345339706", "Lord of the Rings: Fellowship of the Ring", "J.R.R.", "Tolkien", "they're taking hobbits to isengard");
        Book theComingStorm = new Book("9781423100188", "The Coming Storm (Pirates of the Caribbean: Jack Sparrow, No. 1)", "Rob", "Kidd", "I've got a jar of dirt");

        HashMap<String, String> params = new HashMap<>();
        params.put("firstname", null);
        params.put("lastName", "");

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).hasSize(5).containsExactlyInAnyOrder(harryPotter,harryPotter2,harryPotter3, fellowShipOfTheRing, theComingStorm);
    }

    @Test
    @DisplayName("Get all books - by author - first and last name - null first and last name")
    void getAllBooks_byAuthor_firstAndLastName_nullFirstAndLastName() {
        HashMap<String, String> params = new HashMap<>();
        params.put("firstname", null);
        params.put("lastName", null);

        List<Book> answer = bookRepository.getAllBooks(params);

        assertThat(answer).isEmpty();
    }
    
    
}
