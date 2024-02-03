package com.assignment.bookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
    private Long isbn;
    private  String title;
    private  String description;
    private String genera;
    private AuthorDTO author;
    private  double price;


    private List<ReviewDTO> reviews;

}
