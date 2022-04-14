package com.library.loan.services.interfaces;

import com.library.loan.models.dtos.books.BookPostDto;
import com.library.loan.models.dtos.books.BookResponse;
import com.library.loan.models.entities.BookEntity;

public interface IBookService {

  public BookResponse create(BookPostDto book);
  
}
