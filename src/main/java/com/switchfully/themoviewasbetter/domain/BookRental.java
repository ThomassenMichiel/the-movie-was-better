package com.switchfully.themoviewasbetter.domain;


import java.time.LocalDate;
import java.util.Objects;

public class BookRental {
    //VARIABLES
    private String id;
    private Member member;
    private Book book;
    private LocalDate returnDate;
    //CONSTRUCTOR


    public BookRental(String id, Member member, Book book, LocalDate returnDate) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.returnDate = returnDate;
    }

    //METHODS
    public Book getBook() {
        return book;
    }

    public Member getMember() {
        return member;
    }

    public String getId() {
        return id;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRental that = (BookRental) o;
        return Objects.equals(book, that.book) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, id);
    }

}
