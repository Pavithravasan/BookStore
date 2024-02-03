/*
package com.assignment.bookStore.service;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.dto.BookDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.dto.ReviewDTO;
import com.assignment.bookStore.entity.Book;
import com.assignment.bookStore.repository.AuthorRepository;
import com.assignment.bookStore.repository.BookRepository;
import com.assignment.bookStore.service.serviceImpl.AuthorServiceImpl;
import com.assignment.bookStore.service.serviceImpl.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Disabled
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @InjectMocks
    private AuthorServiceImpl authorService;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    private BookDTO storyBook;
    private BookDTO frictionBook;
    private ReviewDTO reviewDTO;

    private ModelMapper modelMapper=new ModelMapper();

    @BeforeEach
    public  void init(){
        storyBook=BookDTO.builder().title("HarryPotter").description("It is an amazing novel")
                .price(999.00).genera("fantasy")
                .author(AuthorDTO.builder().name("JK Rowling").
                        details("An American author who has won many awards").build()).build();
        frictionBook=BookDTO.builder().title("Spiderman").description("It is a book which describes the life of spider man")
                .price(999.00).genera("fantasy")
                .author(AuthorDTO.builder().name("James").
                        details("An American author who has won many awards").build()).build();
        reviewDTO=new ReviewDTO();
        reviewDTO.setBookReview("Amazing book");
        reviewDTO.setStarRating(5);
        storyBook.setReviews(List.of(reviewDTO));
    }

    @Test
    void createNewBook() throws Exception {

        when(bookRepository.save(any(Book.class))).thenReturn(modelMapper.map(storyBook,Book.class));
       BookDTO book= bookService.saveBook(storyBook);
       assertNotNull(book);
       assertThat(book.getGenera()).isEqualTo(storyBook.getGenera());
    }



    @Test
    void createNewBookReview() throws Exception {

        when(bookService.saveReview(any(ReviewDTO.class),anyLong())).thenReturn(reviewDTO);



    }
    @Test
    void shouldFetchAllBooks() throws Exception {
        List<BookDTO> list = new ArrayList<>();
        list.add(storyBook);
        list.add(frictionBook);
        //Act
        when(bookService.getAllBooks()).thenReturn(list);



    }

    @Test
    void shouldFetchBookById() throws Exception {
        when(bookService.getBookById(anyLong())).thenReturn(storyBook);


    }

    @Test
    void shouldDeleteBookById() throws Exception {
        doNothing().when(bookService).deleteBookById(anyLong());
      }

    @Test
    void shouldUpdateBook() throws Exception {
        when(bookService.updateBook(anyLong(),any(BookDTO.class))).thenReturn(storyBook);
      }
}
*/
