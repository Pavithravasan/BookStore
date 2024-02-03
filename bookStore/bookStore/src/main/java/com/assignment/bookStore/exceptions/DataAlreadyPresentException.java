package com.assignment.bookStore.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataAlreadyPresentException extends RuntimeException{
    public DataAlreadyPresentException(String message){
        super(message);
    }
}
