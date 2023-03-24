package com.switchfully.themoviewasbetter.controller;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.domain.Member;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.dto.RentedBookDTO;
import com.switchfully.themoviewasbetter.mapper.BookRentalMapper;
import com.switchfully.themoviewasbetter.repository.BookRentalRepository;
import com.switchfully.themoviewasbetter.repository.MemberRepository;
import com.switchfully.themoviewasbetter.security.Role;
import com.switchfully.themoviewasbetter.security.SecurityService;
import com.switchfully.themoviewasbetter.service.BookRentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookRentalControllerTest {
    private BookRentalController controller;
    private LocalDate localDate = LocalDate.now();
    private Member member01 = new Member();
    private Member member02 = new Member();

    @BeforeEach
    void setUp() {
        MemberRepository repo = new MemberRepository();
        LocalDate localDate = LocalDate.now();

        BookRentalRepository repoBooks = new BookRentalRepository();

        controller = new BookRentalController(new BookRentalService(new BookRentalRepository(), new BookRentalMapper(), new SecurityService(repo)));

        Member member00 = new Member();
        Member librarian00 = new Member();
        librarian00.setRole(Role.LIBRARIAN);
        librarian00.setEmail("pp@mail.com");
        librarian00.setPassword("XXX");
        repo.create(librarian00);
        Book book00 = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");

        BookRental bookRent00 = new BookRental("0", member00, book00, localDate);
        repoBooks.create(bookRent00);

    }


    @Test
    @DisplayName("Get all rentals from list OK?")
    void getAllRentals() {

        Book book00 = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        BookRentalDTO bookRentalDTO00 = new BookRentalDTO("0", member01, book00, localDate);
        BookRentalDTO bookRentalDTO01 = new BookRentalDTO("1", member01, book00, localDate);
        BookRentalDTO bookRentalDTO02 = new BookRentalDTO("2", member02, book00, localDate);

        controller.lend(bookRentalDTO00);
        controller.lend(bookRentalDTO01);
        controller.lend(bookRentalDTO02);

        HashMap<String, String> params = new HashMap<>();
        params.put("all", member01.getEmail());

        List<BookRentalDTO> answer = controller.getAllBookRentals("BASIC cHBAbWFpbC5jb206WFhY", params);

        assertThat(answer).hasSize(3);


    }

    @Test
    void returnBookRental() {
        Book book00 = new Book("9780747532699", "Harry Potter and the Philosopher's Stone", "J.K.", "Rowling", "He's a magical boy living in the stair's closet");
        BookRentalDTO bookRentalDTO00 = new BookRentalDTO("0", member01, book00, localDate);
        BookRentalDTO bookRentalDTO01 = new BookRentalDTO("1", member01, book00, localDate);

        controller.lend(bookRentalDTO00);
        controller.lend(bookRentalDTO01);

        BookRentalMapper mapper = new BookRentalMapper();

        RentedBookDTO answer = controller.getRentedBook(bookRentalDTO00.getId());

        assertThat(answer).isEqualTo(mapper.toRentedBookDTO(mapper.toDomain(bookRentalDTO00)));

    }
}
