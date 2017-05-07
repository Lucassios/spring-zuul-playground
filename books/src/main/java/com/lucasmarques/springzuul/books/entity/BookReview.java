package com.lucasmarques.springzuul.books.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by lucascmarques on 06/05/17.
 */
@Data
@Entity
public class BookReview {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @NotNull
    private Book book;

    @NotNull
    @Column(length = 400)
    private String review;

}
