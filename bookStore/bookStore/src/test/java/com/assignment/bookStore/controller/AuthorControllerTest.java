package com.assignment.bookStore.controller;

import com.assignment.bookStore.BookStoreApplication;
import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.security.service.JwtService;
import com.assignment.bookStore.service.AuthorService;
import com.assignment.bookStore.service.AuthorizationService;
import com.assignment.bookStore.service.BookService;
import com.assignment.bookStore.service.serviceImpl.AuthorizationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
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
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private AuthorizationServiceImpl authorizationService;
    @MockBean
    private JwtService jwtService;
    @BeforeEach
    void init(){
         mockMvc= MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
                 authorOne=AuthorDTO.builder().authorId(1L).name("JK Rowling").details("She is the author of HarryPotter the most famous novel").build();
        authorTwo=AuthorDTO.builder().authorId(1L).name("Sujatha").details("The most famous poet of the 20th century").build();
    }

    @WithMockUser(username = "spring")
    @Test
    void addAuthor() throws Exception {
        when(authorService.saveAuthor(any(AuthorDTO.class))).thenReturn(authorOne);
        this.mockMvc.perform(post("/api/author").with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorOne)))
                .andDo(print())
                .andExpect(status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.name",is(authorOne.getName())));
    }
    @WithMockUser(username = "spring")
    @Test
    void getAuthorByIdTest() throws Exception {
        when(authorService.getAuthorById(anyLong())).thenReturn(authorTwo);
        this.mockMvc.perform(get("/api/author/{id}",1L).with(csrf())).andDo(print()).andExpect(status().isOk())
                . andExpect(MockMvcResultMatchers.jsonPath("$.data.name",is(authorTwo.getName())));
    }
}
