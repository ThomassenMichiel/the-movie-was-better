package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.dto.BookDTO;
import com.switchfully.themoviewasbetter.mapper.BookMapper;
import com.switchfully.themoviewasbetter.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookService(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.getAllBooks().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
