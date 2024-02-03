package com.assignment.bookStore.exceptions;

import com.assignment.bookStore.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value= NotFoundException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(NotFoundException ex){
        ErrorDTO errorDTO=new ErrorDTO(ex.getMessage(),HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value= DataAlreadyPresentException.class)
    public ResponseEntity<ErrorDTO> handleDataAlreadyPresentException(DataAlreadyPresentException ex){
        ErrorDTO errorDTO=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value= RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRunTimeException(RuntimeException ex){
        ErrorDTO errorDTO=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
