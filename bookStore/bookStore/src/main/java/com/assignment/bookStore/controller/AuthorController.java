package com.assignment.bookStore.controller;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.dto.ResponseDTO;
import com.assignment.bookStore.enums.Status;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDto){
        return new ResponseEntity<>(authorService.saveAuthor(authorDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseDTO<AuthorDTO> getByAuthorId(@PathVariable Long id) throws NotFoundException {
        return new ResponseDTO<>(Status.SUCCESS,authorService.getAuthorById(id),"Successfully fetched author details");
    }
}
