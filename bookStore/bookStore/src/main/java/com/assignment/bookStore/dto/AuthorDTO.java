package com.assignment.bookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthorDTO {
    private Long authorId;
    private  String name;
    private  String details;

}
