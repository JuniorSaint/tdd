package com.library.loan.services.implementations;

import com.library.loan.models.dtos.books.BookPostDto;
import com.library.loan.models.dtos.books.BookResponse;
import com.library.loan.models.entities.BookEntity;
import com.library.loan.repositories.BookRepository;
import com.library.loan.services.interfaces.IBookService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class BookService implements IBookService {

  @Autowired
  private BookRepository reposotory;

  @Autowired
  private ModelMapper mapper;

  @Override
  public BookResponse create(BookPostDto book) {
    BookEntity bookEntity = new BookEntity();
    mapper.map(book, bookEntity);
    BookEntity response = reposotory.save(bookEntity);
    BookResponse bookResponse = new BookResponse();
    mapper.map(response, bookResponse);
    return bookResponse;
  }

}
