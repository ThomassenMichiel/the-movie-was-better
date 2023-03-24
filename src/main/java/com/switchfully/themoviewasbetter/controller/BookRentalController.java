package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.dto.RentedBookDTO;
import com.switchfully.themoviewasbetter.dto.ReturnedBookRentalDTO;
import com.switchfully.themoviewasbetter.service.BookRentalService;
import org.springframework.web.bind.annotation.*;

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
        return service.create(newRental);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public ReturnedBookRentalDTO delete(@PathVariable String id){
        return new ReturnedBookRentalDTO(service.delete(id));
    }
    @GetMapping("/{isbn}")
    @ResponseStatus(OK)
    public RentedBookDTO getRentedBook(@RequestParam String isbn){
        return service.find(isbn);
    }


    @GetMapping()
    @ResponseStatus(OK)
    public List<BookRentalDTO> getAllBookRentals(@RequestHeader String authorization, @RequestParam Map<String, String> params) {
        return service.findAll(authorization, params);
    }
}
