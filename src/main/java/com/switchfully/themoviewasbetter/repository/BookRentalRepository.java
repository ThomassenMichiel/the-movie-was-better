package com.switchfully.themoviewasbetter.repository;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.domain.Member;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class BookRentalRepository {
    private final HashMap<String, BookRental> repository;

    public BookRentalRepository() {
        repository = new HashMap<>();
        BookRental aBook = new BookRental("0", new Member(), new Book("a book", "", "", "", ""), LocalDate.of(1990, 10, 10));
        repository.put(aBook.getId(), aBook);
    }

    public BookRental create(BookRental bookRental) {
        repository.put(bookRental.getId(), bookRental);
        return bookRental;
    }

    public BookRental delete(String id) {
        return repository.remove(id);
    }

    public List<BookRental> findAll(Map<String, String> params) {
        Stream<BookRental> rentals = repository.values().stream();
        if (params.containsKey("member")) {
            rentals = rentals.filter(rental -> rental.getMember().getEmail().equals(params.get("member")));
        }
        if (params.containsKey("rentalsDue")) {
            rentals = rentals.filter(rental -> LocalDate.now().isAfter(rental.getReturnDate()));
        }
        return rentals.toList();
    }

    public BookRental findById(String id) {
        return repository.get(id);
    }
}
