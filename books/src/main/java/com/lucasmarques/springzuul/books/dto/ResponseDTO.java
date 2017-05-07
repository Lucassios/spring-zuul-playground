package com.lucasmarques.springzuul.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by lucascmarques on 06/05/17.
 */
@Data
@AllArgsConstructor
public class ResponseDTO<T> extends ResourceSupport {

    private T content;

}
