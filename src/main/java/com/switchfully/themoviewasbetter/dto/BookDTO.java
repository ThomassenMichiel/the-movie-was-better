package com.switchfully.themoviewasbetter.dto;

import java.util.Objects;

public class BookDTO {
    private String isbn;
    private String title;
    private String authorFirstName;
    private String authorLastName;

    public BookDTO(String isbn, String title, String authorFirstName, String authorLastName) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDTO bookDTO)) return false;
        return Objects.equals(isbn, bookDTO.isbn) && Objects.equals(title, bookDTO.title) && Objects.equals(authorFirstName, bookDTO.authorFirstName) && Objects.equals(authorLastName, bookDTO.authorLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, authorFirstName, authorLastName);
    }
}
