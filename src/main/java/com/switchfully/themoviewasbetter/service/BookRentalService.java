package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.mapper.BookRentalMapper;
import com.switchfully.themoviewasbetter.repository.BookRentalRepository;
import com.switchfully.themoviewasbetter.security.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_DUE_RENTALS;
import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_USERS;

@Service
public class BookRentalService {
    private final BookRentalRepository bookRentalRepository;
    private final BookRentalMapper mapper;
    private final SecurityService securityService;

    public BookRentalService(BookRentalRepository bookRentalRepository, BookRentalMapper mapper, SecurityService securityService) {
        this.bookRentalRepository = bookRentalRepository;
        this.mapper = mapper;
        this.securityService = securityService;
    }

    public List<BookRentalDTO> findAll(String authorization, Map<String, String> params) {
        if (params.containsKey("member")) {
            securityService.validateAuthorization(authorization, GET_ALL_USERS);
        }
        if (params.containsKey("rentalsDue")) {
            securityService.validateAuthorization(authorization, GET_ALL_DUE_RENTALS);
        }
        return bookRentalRepository.findAll(params).stream().map(mapper::toDto).toList();
    }

    public BookRentalDTO create(BookRentalDTO newRental) {
        newRental.setDate(LocalDate.now().plusWeeks(3));
        BookRental rentalToSave = mapper.toDomain(newRental);
        return mapper.toDto(bookRentalRepository.create(rentalToSave));
    }

    public String delete(BookRentalDTO bookRentalToReturn) {
        bookRentalRepository.delete(mapper.toDomain(bookRentalToReturn));
        String message = "Thank you for returning this book.";
        if (bookRentalToReturn.getReturnDate().isBefore(LocalDate.now())){
            message = "You are late in returning this book!";
        }
        return message;
    }
}
