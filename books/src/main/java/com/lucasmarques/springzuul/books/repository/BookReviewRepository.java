package com.lucasmarques.springzuul.books.repository;

import com.lucasmarques.springzuul.books.entity.BookReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lucascmarques on 06/05/17.
 */
public interface BookReviewRepository extends CrudRepository<BookReview, String> {

    @Query("SELECT br FROM BookReview br WHERE br.book.id = ?1")
    List<BookReview> findByBook(String isbn);

}
