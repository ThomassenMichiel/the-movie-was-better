package com.switchfully.themoviewasbetter.domain;

import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;
    private final String authorFirstName;
    private final String authorLastName;
    private final String smallSummary;


    public Book(String isbn, String title, String authorFirstName, String authorLastName, String smallSummary) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.smallSummary = smallSummary;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getSmallSummary() {
        return smallSummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(authorFirstName, book.authorFirstName) && Objects.equals(authorLastName, book.authorLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authorFirstName, authorLastName);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", smallSummary='" + smallSummary + '\'' +
                '}';
    }
}
