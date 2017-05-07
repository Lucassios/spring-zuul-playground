package com.lucasmarques.springzuul.books.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by lucascmarques on 06/05/17.
 */
@Data
@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

}
