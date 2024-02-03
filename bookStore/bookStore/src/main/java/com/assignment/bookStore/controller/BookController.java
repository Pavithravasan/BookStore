package com.assignment.bookStore.controller;

import com.assignment.bookStore.dto.BookDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.dto.ReviewDTO;
import com.assignment.bookStore.enums.Status;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseDTO<List<BookDTO>> getAllBooks() throws Exception{
        return new ResponseDTO<>(Status.SUCCESS,bookService.getAllBooks(),"Fetched List of Books");

    }
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
     public ResponseDTO<BookDTO> saveBook(@Valid @RequestBody BookDTO bookDto) throws NotFoundException {
        return new ResponseDTO<>(Status.SUCCESS,bookService.saveBook(bookDto),"Book saved successfully");
    }
    @GetMapping("/{isbn}")
    public ResponseDTO<BookDTO> getBookById(@PathVariable long isbn) throws Exception{
        return new ResponseDTO<>(Status.SUCCESS,bookService.getBookById(isbn),"Fetched List of Books");
    }
    @PutMapping("/{isbn}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDTO<BookDTO> updateBookById(@PathVariable long isbn, @Valid @RequestBody BookDTO bookDto) throws NotFoundException {
        return new ResponseDTO<>(Status.SUCCESS,bookService.updateBook(isbn,bookDto),"Fetched List of Books");
    }
    @DeleteMapping("/{isbn}")
    @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseDTO deleteBookById(@PathVariable long isbn) throws NotFoundException {
        bookService.deleteBookById(isbn);
        return new ResponseDTO<>(Status.SUCCESS,"Deleted book");
    }
    @PostMapping("/{isbn}/reviews")
    public ResponseDTO<ReviewDTO> saveReviews(@RequestBody ReviewDTO reviewDTO,@PathVariable Long isbn) throws NotFoundException {
        return new ResponseDTO<>(Status.SUCCESS,bookService.saveReview(reviewDTO,isbn),"Book saved successfully");
    }
}
