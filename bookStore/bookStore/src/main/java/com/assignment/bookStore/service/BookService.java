package com.assignment.bookStore.service;

import com.assignment.bookStore.dto.BookDTO;
import com.assignment.bookStore.dto.ReviewDTO;
import com.assignment.bookStore.exceptions.NotFoundException;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks() ;
    BookDTO getBookById(long isbn) ;

    BookDTO saveBook(BookDTO book) throws NotFoundException;

    ReviewDTO saveReview(ReviewDTO reviewDTO,Long isbn);

    void deleteBookById(long isbn) throws NotFoundException;

    BookDTO updateBook(long isbn, BookDTO bookDto) throws NotFoundException;
}
