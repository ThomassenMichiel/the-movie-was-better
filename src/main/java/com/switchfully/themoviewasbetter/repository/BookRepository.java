package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BookRepository {
    private final HashMap<String, Book> repository;

    public BookRepository() {
        this.repository = new HashMap<>();

        Book harryPotter = new Book("978-0-7475-3269-9", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        repository.put(harryPotter.getIsbn(), harryPotter);
        Book harryPotter2 = new Book("0-7475-3849-2", "Harry Potter and the Chamber of Secrets", "J.K.", "Rowling", "A secret chamber opened");
        repository.put(harryPotter2.getIsbn(), harryPotter2);
        Book harryPotter3 = new Book("0-7475-4215-5", "Harry Potter and the Prisoner of Azkaban","J.K.", "Rowling", "Harry's been naughty");
        repository.put(harryPotter3.getIsbn(), harryPotter3);

    }

    public List<Book> getAllBooks() {
        return repository.values().stream().toList();
    }
}
