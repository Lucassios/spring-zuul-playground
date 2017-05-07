package com.lucasmarques.springzuul.books.repository;

import com.lucasmarques.springzuul.books.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lucascmarques on 06/05/17.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    @Query("SELECT b FROM Book b " +
            "JOIN b.authors a " +
            "JOIN b.keywords kw " +
            "WHERE (?1 IS NULL OR b.title = ?1) " +
            "AND (?2 IS NULL OR a.name = ?2) " +
            "AND (?3 IS NULL OR kw.keyword = ?3)")
    List<Book> findByFilter(String title, String author, String keyword);

}
