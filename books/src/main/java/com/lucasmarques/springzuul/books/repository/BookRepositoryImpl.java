package com.lucasmarques.springzuul.books.repository;

import com.lucasmarques.springzuul.books.entity.Book;

import java.util.List;

/**
 * Created by lucascmarques on 06/05/17.
 */
public class BookRepositoryImpl implements BookRepositoryCustom {

    @Override
    public List<Book> searchByFilter(String title, String author, String keyword) {
        return null;
    }
}
