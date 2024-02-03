package com.assignment.bookStore.dto;

import com.assignment.bookStore.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private Status status;
    private T data;

    public ResponseDTO(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    private String message;

}
