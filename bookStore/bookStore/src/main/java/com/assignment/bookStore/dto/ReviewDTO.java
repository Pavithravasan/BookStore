package com.assignment.bookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long review_id;
   // private User user;
    private  String bookReview;
    private Integer starRating;
}
