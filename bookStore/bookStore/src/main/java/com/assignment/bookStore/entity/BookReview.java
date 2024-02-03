package com.assignment.bookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="tbl_book_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookReview {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long review_id;
    /*@JoinColumn(name="user_id",referencedColumnName = "user_id")
    @OneToOne
    private  User user;*/
    @Column(name="book_review")
    private  String bookReview;
    @JoinColumn(name="book_id",referencedColumnName = "isbn")
    @ManyToOne
    private Book book;

    @Column(name="star_rating")
    private int starRating;
}
