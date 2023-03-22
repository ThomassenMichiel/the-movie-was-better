package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.dto.BookDTO;
import com.switchfully.themoviewasbetter.exceptions.BookNotFoundException;
import com.switchfully.themoviewasbetter.mapper.BookMapper;
import com.switchfully.themoviewasbetter.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.getAllBooks(new HashMap<>()).stream().map(mapper::toDto).toList();
    }

    public BookDTO findByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(BookNotFoundException::new);
        return mapper.toDto(book);
    }
}
