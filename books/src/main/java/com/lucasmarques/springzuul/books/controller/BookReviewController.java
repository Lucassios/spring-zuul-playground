package com.lucasmarques.springzuul.books.controller;

import com.lucasmarques.springzuul.books.dto.ResponseDTO;
import com.lucasmarques.springzuul.books.entity.Book;
import com.lucasmarques.springzuul.books.entity.BookReview;
import com.lucasmarques.springzuul.books.repository.BookRepository;
import com.lucasmarques.springzuul.books.repository.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by lucascmarques on 06/05/17.
 */
@RestController
public class BookReviewController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @RequestMapping(value = "/book/{isbn}/reviews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<List<BookReview>> getReviews(@PathVariable String isbn) {
        List<BookReview> reviews = bookReviewRepository.findByBook(isbn);
        ResponseDTO<List<BookReview>> response = new ResponseDTO<List<BookReview>>(reviews);
        response.add(linkTo(methodOn(BookReviewController.class).getReviews(isbn)).withSelfRel());
        return response;
    }

    @RequestMapping(value = "/book/{isbn}/review", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<BookReview> createReview(@PathVariable String isbn, @RequestBody BookReview bookReview) {
        Book book = bookRepository.findOne(isbn);
        bookReview.setBook(book);
        bookReview = bookReviewRepository.save(bookReview);
        ResponseDTO<BookReview> response = new ResponseDTO<BookReview>(bookReview);
        response.add(linkTo(methodOn(BookReviewController.class).createReview(isbn, bookReview)).withSelfRel());
        return response;
    }

}
