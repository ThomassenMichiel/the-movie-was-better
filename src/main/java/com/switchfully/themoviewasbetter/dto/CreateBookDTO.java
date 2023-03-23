package com.switchfully.themoviewasbetter.dto;

import com.switchfully.themoviewasbetter.exceptions.FieldIsEmptyException;

import java.util.Objects;

public class CreateBookDTO {
    private String isbn;
    private final String title;
    private final String authorFirstName;
    private final String authorLastName;
    private final String smallSummary;

    public CreateBookDTO(String isbn, String title, String authorFirstName, String authorLastName, String smallSummary) {
        validateISBN(isbn);
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

    public final void validateISBN(String isbn){
        if(isbn.trim().isEmpty()){
             throw new FieldIsEmptyException("isbn");
        }
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateBookDTO that)) return false;
        return Objects.equals(isbn, that.isbn) && Objects.equals(title, that.title) && Objects.equals(authorFirstName, that.authorFirstName) && Objects.equals(authorLastName, that.authorLastName) && Objects.equals(smallSummary, that.smallSummary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authorFirstName, authorLastName, smallSummary);
    }
}
