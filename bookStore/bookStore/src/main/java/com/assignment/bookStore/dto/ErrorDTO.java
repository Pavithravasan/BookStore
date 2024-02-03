package com.assignment.bookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {
    private String errorMessage;
    private HttpStatus errorCode;
    private LocalDateTime timeStamp;
}
