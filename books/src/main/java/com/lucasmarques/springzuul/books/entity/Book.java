package com.lucasmarques.springzuul.books.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by lucascmarques on 06/05/17.
 */
@Data
@Entity
public class Book {

    @Id
    @Size(max = 13)
    @NotNull
    private String isbn;

    @NotNull
    private String title;

    @Column(length = 400)
    private String description;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Author> authors;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Keyword> keywords;

    private Integer publishedYear;

    private String publishingCompany;

    private String edition;

    @OneToMany
    private List<Book> relatedBooks;

}
