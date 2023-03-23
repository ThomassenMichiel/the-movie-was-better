package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.BookRentalService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_USERS;
import static com.switchfully.themoviewasbetter.security.Feature.GET_ALL_DUE_RENTALS;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("rental")
public class BookRentalController {
    private BookRentalService service;
    private final SecurityService securityService;

    public BookRentalController(BookRentalService service, SecurityService securityService) {
        this.service = service;
        this.securityService = securityService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/make")
    public BookRentalDTO lendBookRental(@RequestBody BookRentalDTO newRental){
        newRental.setDate(newRental.getReturnDate().plusWeeks(3));
        return service.saveRental(newRental);
    }

    @GetMapping("return")
    public String returnBookRental(@RequestBody BookRentalDTO bookRentalToReturn){
        service.returnBookRental(bookRentalToReturn);
        if (bookRentalToReturn.getReturnDate().isBefore(LocalDate.now())){
            return "You are late in returning this book!";
        }
        return "Thank you for returning this book.";
    }

    @GetMapping("getallrentals")
    @ResponseStatus(OK)
    public List<BookRentalDTO> getAllBookRentals() {
        return service.getAllRentals();
    }

    @GetMapping("getallrentalsbymember")
    @ResponseStatus(OK)
    public List<BookRentalDTO> getAllBookRentalsByMember(@RequestHeader String authorization, @RequestParam Member member) {
        securityService.validateAuthorization(authorization, GET_ALL_USERS);
        return service.getAllBookRentalsByMember(member);
            }

    @GetMapping("getallrentalsdue")
    @ResponseStatus(OK)
    public List<BookRentalDTO> getAllBookRentalsByDueDate(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, GET_ALL_DUE_RENTALS);
        return service.getAllBookRentalsByDueDate();
    }


}
