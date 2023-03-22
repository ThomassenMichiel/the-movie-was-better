package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BookRepository {
    private HashMap<String, Book> repository = new HashMap<>();

    public List<Book> getAllBooks() {
        return repository.values().stream().toList();
    }
}
