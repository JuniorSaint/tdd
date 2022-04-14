package com.library.loan.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.library.loan.exceptions.ApiErros;
import com.library.loan.models.dtos.books.BookPostDto;
import com.library.loan.models.dtos.books.BookResponse;
import com.library.loan.models.entities.BookEntity;
import com.library.loan.services.interfaces.IBookService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/books")
public class booksController {

  @Autowired
  private IBookService service;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookResponse create(@RequestBody @Valid BookPostDto book) {
    
    BookResponse response = service.create(book);

    return response;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiErros handleValidationException(MethodArgumentNotValidException ex){
    BindingResult bindingResult = ex.getBindingResult();
   return new ApiErros(bindingResult);

  }

}
