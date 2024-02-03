/*
package com.assignment.bookStore.controller;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.service.AuthorService;
import com.assignment.bookStore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
@Disabled
public class AuthorControllerTest {
    @MockBean
    private AuthorService authorService;
    @MockBean
    private BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private AuthorDTO authorOne;
    private AuthorDTO authorTwo;

    @BeforeEach
    void init(){
        authorOne=AuthorDTO.builder().authorId(1L).name("JK Rowling").details("She is the author of HarryPotter the most famous novel").build();
        authorTwo=AuthorDTO.builder().authorId(1L).name("Sujatha").details("The most famous poet of the 20th century").build();
    }
    @Test
    void addAuthor() throws Exception {
        when(authorService.saveAuthor(any(AuthorDTO.class))).thenReturn(authorOne);
        this.mockMvc.perform(post("/api/author").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorOne)))
                .andDo(print())
                .andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name",is(authorOne.getName())));
    }

    @Test
    void getAuthorByIdTest() throws Exception {
        when(authorService.getAuthorById(anyLong())).thenReturn(authorTwo);
        this.mockMvc.perform(get("/api/author/{id}",1L)).andDo(print()).andExpect(status().isOk())
                . andExpect(MockMvcResultMatchers.jsonPath("$.data.name",is(authorTwo.getName())));
    }
}
*/
