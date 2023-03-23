package com.switchfully.themoviewasbetter.mapper;

import com.switchfully.themoviewasbetter.domain.Book;
import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRentalMapper {
    public BookRentalDTO toDto(BookRental bookRental){
        return new BookRentalDTO(bookRental.getId(), bookRental.getMember(), bookRental.getBook(), bookRental.getReturnDate());
    }

    public List<BookRentalDTO> listToDto(List<BookRental> bookRentalList){
        List<BookRentalDTO> bookRentalDtoList = new ArrayList<>();
        for (BookRental bookRental : bookRentalList) {
            BookRentalDTO bookRentalDTO = toDto(bookRental);
            bookRentalDtoList.add(bookRentalDTO);
        }
        return bookRentalDtoList;
   }

    public BookRental toBookRental(BookRentalDTO bookRentalDto){
        return new BookRental(bookRentalDto.getId(), bookRentalDto.getMember(), bookRentalDto.getBook(), bookRentalDto.getReturnDate());
    }

}
