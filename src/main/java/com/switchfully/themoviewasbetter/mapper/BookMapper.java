package com.switchfully.themoviewasbetter.mapper;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.dto.BookDTO;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public BookDTO toDto(Book book) {
        return new BookDTO(book.getIsbn(), book.getTitle(), book.getAuthorFirstName(), book.getAuthorLastName());
    }
}
