package com.lucasmarques.springzuul.books.controller;

import com.lucasmarques.springzuul.books.dto.BookReviewDTO;
import com.lucasmarques.springzuul.books.dto.ResponseDTO;
import com.lucasmarques.springzuul.books.entity.Book;
import com.lucasmarques.springzuul.books.entity.BookReview;
import com.lucasmarques.springzuul.books.repository.BookRepository;
import com.lucasmarques.springzuul.books.repository.BookReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseDTO<List<BookReviewDTO>> getReviews(@PathVariable String isbn) {
        List<BookReviewDTO> reviews = transform(bookReviewRepository.findByBook(isbn));
        ResponseDTO<List<BookReviewDTO>> response = new ResponseDTO<List<BookReviewDTO>>(reviews);
        response.add(linkTo(methodOn(BookReviewController.class).getReviews(isbn)).withSelfRel());
        return response;
    }

    @RequestMapping(value = "/book/{isbn}/review", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<BookReviewDTO> createReview(@PathVariable String isbn, @RequestBody BookReview bookReview) {
        Book book = bookRepository.findOne(isbn);
        bookReview.setBook(book);
        bookReview = bookReviewRepository.save(bookReview);
        ResponseDTO<BookReviewDTO> response = new ResponseDTO<BookReviewDTO>(transform(bookReview));
        response.add(linkTo(methodOn(BookReviewController.class).createReview(isbn, bookReview)).withSelfRel());
        return response;
    }

    public List<BookReviewDTO> transform(List<BookReview> reviews) {
        List<BookReviewDTO> reviewsDTO = new ArrayList<BookReviewDTO>();
        for (BookReview review : reviews) {
            reviewsDTO.add(transform(review));
        }
        return reviewsDTO;
    }

    public BookReviewDTO transform(BookReview bookReview) {
        return new BookReviewDTO(bookReview.getReview());
    }

}
