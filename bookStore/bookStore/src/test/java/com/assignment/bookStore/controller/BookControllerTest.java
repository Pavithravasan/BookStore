package com.assignment.bookStore.controller;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.dto.BookDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.dto.ReviewDTO;
import com.assignment.bookStore.entity.Book;
import com.assignment.bookStore.security.service.JwtService;
import com.assignment.bookStore.service.AuthorService;
import com.assignment.bookStore.service.AuthorizationService;
import com.assignment.bookStore.service.BookService;
import com.assignment.bookStore.service.serviceImpl.AuthorizationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.writer.JsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
public class BookControllerTest {
    @MockBean
    public BookService bookService;
    @MockBean
    public AuthorService authorService;
    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public MockMvc mockMvc;

    private BookDTO storyBook;
    private BookDTO frictionBook;
    private  ReviewDTO reviewDTO;
    @MockBean
    private AuthorizationServiceImpl authorizationService;
    @MockBean
    private JwtService jwtService;
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

    @WithMockUser(username = "spring")
    @Test
    void createNewBook() throws Exception {

        when(bookService.saveBook(any(BookDTO.class))).thenReturn(storyBook);

        this.mockMvc.perform(post("/api/books").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storyBook)))
                .andExpect(status().isOk());

    }
    @WithMockUser(username = "spring")
    @Test
    void createNewBookReview() throws Exception {

        when(bookService.saveReview(any(ReviewDTO.class),anyLong())).thenReturn(reviewDTO);

        this.mockMvc.perform(post("/api/books/{isbn}/reviews",1L).with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDTO)))
                .andExpect(status().isOk());

    }
    @WithMockUser(username = "spring")
    @Test
    void shouldFetchAllBooks() throws Exception {
        List<BookDTO> list = new ArrayList<>();
        list.add(storyBook);
        list.add(frictionBook);
       //Act
        when(bookService.getAllBooks()).thenReturn(list);
        String content= this.mockMvc.perform(get("/api/books").with(csrf()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<BookDTO> bookDTOList= (List<BookDTO>) new ObjectMapper().readValue(content, ResponseDTO.class).getData();
        assertEquals(2,bookDTOList.size());


    }
    @WithMockUser(username = "spring")
    @Test
    void shouldFetchBookById() throws Exception {
        when(bookService.getBookById(anyLong())).thenReturn(storyBook);
        this.mockMvc.perform(get("/api/books/{isbn}", 1L))
               .andDo(print())
                .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.data.genera", is(storyBook.getGenera())));


    }
    @WithMockUser(username = "spring")
    @Test
    void shouldDeleteBookById() throws Exception {
        doNothing().when(bookService).deleteBookById(anyLong());
        this.mockMvc.perform(delete("/api/books/{isbn}", 1L).with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "spring")
    void shouldUpdateBook() throws Exception {
        when(bookService.updateBook(anyLong(),any(BookDTO.class))).thenReturn(storyBook);
        this.mockMvc.perform(put("/api/books/{isbn}", 1L).with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(storyBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title", is(storyBook.getTitle())))
                .andExpect(jsonPath("$.data.genera", is(storyBook.getGenera())));
    }
}
