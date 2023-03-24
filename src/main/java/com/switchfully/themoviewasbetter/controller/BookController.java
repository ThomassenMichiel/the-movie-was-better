package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.BookDTO;
import com.switchfully.themoviewasbetter.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<BookDTO> findAllBooks(@RequestParam Map<String, String> params) {
        return bookService.findAll(params);
    }

    @GetMapping("/{isbn}")
    @ResponseStatus(OK)
    public BookDTO getBookDetails(@PathVariable String isbn){
        return bookService.findByIsbn(isbn);
    }


}
