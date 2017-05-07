package com.lucasmarques.springzuul.books.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by lucascmarques on 06/05/17.
 */
@Data
@Entity
public class Keyword {

    @Id
    private String keyword;

}
