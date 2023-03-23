package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.BookDTO;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.mapper.BookMapper;
import com.switchfully.themoviewasbetter.mapper.BookRentalMapper;
import com.switchfully.themoviewasbetter.repository.BookRentalRepository;
import com.switchfully.themoviewasbetter.repository.BookRepository;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.BookRentalService;
import com.switchfully.themoviewasbetter.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookRentalControllerTest {
    private BookRentalController controller;

    @BeforeEach
    void setUp() {
        controller = new BookRentalController(new BookRentalService(new BookRentalRepository(), new BookRentalMapper()), new SecurityService(new MemberRepository()));
    }
    LocalDate localDate = LocalDate.now();
    Member member01 = new Member();
    Member member02 = new Member();
    Book book00 = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
    @Test
    @DisplayName("Get all rentals from list OK?")
    void getAllRentals(){

//        BookRental rental00 = new BookRental("1",member01, book00,localDate );
//        BookRental rental01 = new BookRental("2",member01, book00,localDate );
//        BookRental rental02 = new BookRental("3",member01, book00,localDate );

        BookRentalDTO bookRentalDTO00 = new BookRentalDTO("1",member01, book00,localDate );
        BookRentalDTO bookRentalDTO01 = new BookRentalDTO("2",member01, book00,localDate );
        BookRentalDTO bookRentalDTO02 = new BookRentalDTO("3",member02, book00,localDate );


        controller.lendBookRental(bookRentalDTO00);
        controller.lendBookRental(bookRentalDTO01);
        controller.lendBookRental(bookRentalDTO02);

        List<BookRentalDTO> answer = controller.getAllBookRentals();

        assertThat(answer).hasSize(3);


    }

    @Test
    void returnBookRental() {

        BookRentalDTO bookRentalDTO00 = new BookRentalDTO("1",member01, book00,localDate );

        controller.returnBookRental(bookRentalDTO00);

        List<BookRentalDTO> answer = controller.getAllBookRentals();

        assertThat(answer).hasSize(2);

    }
    @Test
    void getAllBookRentalsByMember() {


    }

    @Test
    void getAllBookRentalsByDueDate() {

        List<BookRentalDTO> answer = controller.getAllBookRentalsByDueDate(String.valueOf(localDate.minusWeeks(2)));

        assertThat(answer).hasSize(2);


    }


}