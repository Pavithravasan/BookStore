package com.assignment.bookStore.service.serviceImpl;

import com.assignment.bookStore.dto.AuthorDTO;
import com.assignment.bookStore.entity.Author;
import com.assignment.bookStore.exceptions.NotFoundException;
import com.assignment.bookStore.repository.AuthorRepository;
import com.assignment.bookStore.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private ModelMapper modelMapper=new ModelMapper();
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDto) {
        Author author= modelMapper.map(authorDto,Author.class);

        Author author1= authorRepository.save(author);
        AuthorDTO authorDTO1 = modelMapper.map(author1, AuthorDTO.class);
        return authorDTO1;
    }

    @Override
    public AuthorDTO getAuthorById(long id) throws NotFoundException {
        try{
        Author author1= authorRepository.findById(id).get();
        AuthorDTO authorDTO1 = modelMapper.map(author1, AuthorDTO.class);
        return authorDTO1;
    }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }}
    @Override
    public Author getAuthorByName(String name) {
        Optional<Author> author1= authorRepository.findByName(name);
        if(author1.isPresent()){
       return author1.get();}
        else{
            return null;
        }
    }
}
