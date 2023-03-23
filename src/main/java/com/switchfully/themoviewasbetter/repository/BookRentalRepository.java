package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.BookRental;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BookRentalRepository {
    private HashMap<String, BookRental> repository = new HashMap<>();

    public BookRental createRental(BookRental bookRental){
        repository.put(bookRental.getId() , bookRental);
        return bookRental;
    }
    public void deleteRental(BookRental bookRental){
        repository.remove(bookRental.getId() , bookRental);
    }

    public List<BookRental> getAllBooks() {
        return repository.values().stream().toList();
    }
}
