package com.assignment.bookStore.service;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.entity.Author;
import com.assignment.bookStore.exceptions.NotFoundException;

public interface AuthorService {
    AuthorDTO saveAuthor(AuthorDTO authorDto);

    AuthorDTO getAuthorById(long id) throws NotFoundException;

    Author getAuthorByName(String name);

}
