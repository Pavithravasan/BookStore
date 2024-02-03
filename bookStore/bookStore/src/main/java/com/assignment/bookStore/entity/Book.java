package com.assignment.bookStore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name="book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long isbn;
    @Column(name="title")
    private  String title;
    @Column(name="description")
    private  String description;
    @Column(name="genera")
    private String genera;

    @JoinColumn(name="author_id")
    @ManyToOne
    private  Author author;

    @Column
    private  double price;
    @Column(name="is_available")
    private boolean isAvailable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookReview> bookReviews;

    public Book(String title, String description, String genera, Author author, double price, boolean isAvailable) {
        this.title = title;
        this.description = description;
        this.genera = genera;
        this.author = author;
        this.price = price;
        this.isAvailable = isAvailable;
    }
}
