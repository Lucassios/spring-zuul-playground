package com.lucasmarques.springzuul.books.controller;

import com.lucasmarques.springzuul.books.dto.ResponseDTO;
import com.lucasmarques.springzuul.books.entity.Book;
import com.lucasmarques.springzuul.books.repository.BookRepository;
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
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> listAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @RequestMapping(value = "/books/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<List<Book>> listBooksByFilter(@RequestParam(value = "title", required = false) String title,
                                        @RequestParam(value = "author", required = false) String author,
                                        @RequestParam(value = "keyword", required = false) String keyword) {
        List<Book> booksFound = bookRepository.findByFilter(title, author, keyword);
        ResponseDTO<List<Book>> response = new ResponseDTO<List<Book>>(booksFound);
        response.add(linkTo(methodOn(BookController.class).listBooksByFilter(title, author, keyword)).withSelfRel());
        return response;
    }

    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Book> getBook(@PathVariable String isbn) {
        Book book = bookRepository.findOne(isbn);
        ResponseDTO<Book> response = new ResponseDTO<Book>(book);
        response.add(linkTo(methodOn(BookController.class).getBook(isbn)).withSelfRel());
        return response;
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Book> createBook(@RequestBody Book book) {
        Book bookSaved = bookRepository.save(book);
        ResponseDTO<Book> response = new ResponseDTO<Book>(bookSaved);
        response.add(linkTo(methodOn(BookController.class).createBook(book)).withSelfRel());
        return response;
    }

    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        book.setIsbn(isbn);
        Book bookSaved = bookRepository.save(book);
        ResponseDTO<Book> response = new ResponseDTO<Book>(bookSaved);
        response.add(linkTo(methodOn(BookController.class).updateBook(isbn, book)).withSelfRel());
        return response;
    }

    @RequestMapping(value = "/book/{isbn}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO<Book> deleteBook(@PathVariable String isbn) {
        Book book = new Book();
        book.setIsbn(isbn);
        bookRepository.delete(isbn);
        ResponseDTO<Book> response = new ResponseDTO<Book>(book);
        response.add(linkTo(methodOn(BookController.class).deleteBook(isbn)).withSelfRel());
        return response;
    }

}
