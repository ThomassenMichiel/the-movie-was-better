package com.switchfully.themoviewasbetter.service;

import com.switchfully.themoviewasbetter.domain.BookRental;
import com.switchfully.themoviewasbetter.dto.BookRentalDTO;
import com.switchfully.themoviewasbetter.mapper.BookRentalMapper;
import com.switchfully.themoviewasbetter.repository.BookRentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookRentalService {
    private final BookRentalRepository bookRentalRepository;
    private final BookRentalMapper mapper;

    public BookRentalService(BookRentalRepository bookRentalRepository, BookRentalMapper mapper) {
        this.bookRentalRepository = bookRentalRepository;
        this.mapper = mapper;
    }

    public List<BookRentalDTO> findAll() {
        return bookRentalRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public BookRentalDTO create(BookRentalDTO newRental) {
        BookRental rentalToSave = mapper.toDomain(newRental);
        return mapper.toDto(bookRentalRepository.create(rentalToSave));
    }

    public void delete(BookRentalDTO bookRentalToReturn) {
        bookRentalRepository.delete(mapper.toDomain(bookRentalToReturn));
    }

//    public List<BookRentalDTO> getAllBookRentalsByMember(Member member) {
//        return mapper.toDto(bookRentalRepository.findAll()
//                .stream()
//                .filter(x -> member.equals(x.getMember()))
//                .toList());
//    }
//
//    public List<BookRentalDTO> getAllBookRentalsByDueDate() {
//        return mapper.toDto(bookRentalRepository.findAll()
//                .stream()
//                .filter(x -> LocalDate.now().isAfter(x.getReturnDate()))
//                .toList());
//    }
}
