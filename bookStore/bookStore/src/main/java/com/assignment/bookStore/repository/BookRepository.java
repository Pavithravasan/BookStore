package com.assignment.bookStore.repository;

import com.assignment.bookStore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
   Optional<Book> findByTitleAndAuthorAuthorId(String name, long isbn);

    Optional<Book> findByTitleAndAuthorAuthorIdAndIsbn(String title, Long authorId, Long isbn);

    Optional<Book> findByIsbn(Long isbn);
}
