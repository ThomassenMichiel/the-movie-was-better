package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.exceptions.InvalidISBNException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class BookRepository {
    private final HashMap<String, Book> repository;

    public BookRepository() {
        this.repository = new HashMap<>();

        Book harryPotter = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        repository.put(harryPotter.getIsbn(), harryPotter);
        Book harryPotter2 = new Book("0747538492", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        repository.put(harryPotter2.getIsbn(), harryPotter2);
        Book harryPotter3 = new Book("0747542155", "Harry Potter and the Prisoner of Azkaban", "J.K.", "Rowling", "Harry's been naughty");
        repository.put(harryPotter3.getIsbn(), harryPotter3);

    }

    public List<Book> getAllBooks(Map<String, String> params) {
        Stream<Book> bookStream = repository.values()
                .stream();
        if (params.containsKey("isbn")) {
            isValidSearchIsbn(params.get("isbn"));
            bookStream = bookStream
                    .filter(book -> book.getIsbn().matches(".*" + params.get("isbn") + ".*"));
        }

        if (params.containsKey("title")) {
            bookStream = bookStream
                    .filter(book -> book.getTitle().matches("(?i).*" + params.get("title") + ".*"));
        }

        if (params.containsKey("firstName")) {
            bookStream = bookStream
                    .filter(book -> book.getAuthorFirstName().matches("(?i).*" + params.get("firstName") + ".*"));
        }

        if (params.containsKey("lastName")) {
            bookStream = bookStream
                    .filter(book -> book.getAuthorLastName().matches("(?i).*" + params.get("lastName") + ".*"));
        }


        return bookStream.toList();
    }

    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(repository.get(isbn));
    }

    private void isValidSearchIsbn(String isbn) {
        Pattern isbnPattern = Pattern.compile("^(97[89])\\d{9}[\\dXx]$");
        Matcher isbnMatcher = isbnPattern.matcher(isbn);
        boolean valid = isbnMatcher.find() || (isbn.length() <= 13 && isbn.matches("[^a-zA-Z]*"));
        if (!valid) {
            throw new InvalidISBNException();
        }
    }
}
