package com.lucasmarques.springzuul.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lucascmarques on 20/04/17.
 */
@RestController
@SpringBootApplication
public class BookApplication {

    @RequestMapping(value = "/available")
    public String available() {
        return "Spring in Action";
    }

    @RequestMapping(value = "/checked-out")
    public String checkedOut() {
        return "Spring Boot in Action";
    }

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
