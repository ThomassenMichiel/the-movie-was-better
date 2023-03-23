package com.switchfully.themoviewasbetter.mapper;

import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRentalMapper {
    public BookRentalDTO toDto(BookRental bookRental){
        return new BookRentalDTO(bookRental.getId(), bookRental.getMember(), bookRental.getBook(), bookRental.getReturnDate());
    }

    public List<BookRentalDTO> toDto(List<BookRental> bookRentalList){
        return bookRentalList.stream().map(this::toDto).toList();
   }

    public BookRental toDomain(BookRentalDTO bookRentalDto){
        return new BookRental(bookRentalDto.getId(), bookRentalDto.getMember(), bookRentalDto.getBook(), bookRentalDto.getReturnDate());
    }

}
