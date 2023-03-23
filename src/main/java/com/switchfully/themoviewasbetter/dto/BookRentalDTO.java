package com.switchfully.themoviewasbetter.dto;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.Member;

import java.time.LocalDate;

public class BookRentalDTO {

    private String id;
    private Member member;
    private Book book;
    private LocalDate returnDate;

    public BookRentalDTO(String id, Member member, Book book, LocalDate returnDate) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.returnDate = returnDate;
    }


    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setDate(LocalDate plusWeeks) {
        this.returnDate = plusWeeks;
    }
}
