package com.lucasmarques.springzuul.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by lucascmarques on 07/05/17.
 */
@AllArgsConstructor
@Data
public class BookReviewDTO implements Serializable {

    private String review;

}
