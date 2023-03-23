package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.domain.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Component
public class BookRentalRepository {
    private final HashMap<String, BookRental> repository;

    public BookRentalRepository() {
        repository = new HashMap<>();
        BookRental aBook = new BookRental("0", new Member(), new Book("a book", "", "", "", ""), LocalDate.of(1990,10,10));
        repository.put(aBook.getId(), aBook);
    }

    public BookRental create(BookRental bookRental){
        repository.put(bookRental.getId() , bookRental);
        return bookRental;
    }
    public void delete(BookRental bookRental){
        repository.remove(bookRental.getId() , bookRental);
    }

    public List<BookRental> findAll() {
        return repository.values().stream().toList();
    }
}
