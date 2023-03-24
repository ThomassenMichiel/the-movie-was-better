package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.service.BookRentalService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("rentals")
public class BookRentalController {
    private final BookRentalService service;

    public BookRentalController(BookRentalService service) {
        this.service = service;
    }

    @PostMapping()
    public BookRentalDTO lend(@RequestBody BookRentalDTO newRental){
//        newRental.setDate(newRental.getReturnDate().plusWeeks(3));
        return service.create(newRental);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public String delete(@RequestBody BookRentalDTO bookRentalToReturn){
        service.delete(bookRentalToReturn);
        if (bookRentalToReturn.getReturnDate().isBefore(LocalDate.now())){
            return "You are late in returning this book!";
        }
        return "Thank you for returning this book.";
    }

    @GetMapping()
    @ResponseStatus(OK)
    public List<BookRentalDTO> getAllBookRentals(@RequestParam Map<String, String> params) {
        return service.findAll();
    }

//    @GetMapping("getallrentalsbymember")
//    @ResponseStatus(OK)
//    public List<BookRentalDTO> getAllBookRentalsByMember(@RequestHeader String authorization, @RequestParam Member member) {
//        securityService.validateAuthorization(authorization, GET_ALL_USERS);
//        return service.getAllBookRentalsByMember(member);
//            }
//
//    @GetMapping("getallrentalsdue")
//    @ResponseStatus(OK)
//    public List<BookRentalDTO> getAllBookRentalsByDueDate(@RequestHeader String authorization) {
//        securityService.validateAuthorization(authorization, GET_ALL_DUE_RENTALS);
//        return service.getAllBookRentalsByDueDate();
//    }
}
