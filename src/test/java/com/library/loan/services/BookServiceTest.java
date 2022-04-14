package com.library.loan.services;

import com.library.loan.models.dtos.books.BookPostDto;
import com.library.loan.models.dtos.books.BookResponse;
import com.library.loan.models.entities.BookEntity;
import com.library.loan.repositories.BookRepository;
import com.library.loan.services.implementations.BookService;
import com.library.loan.services.interfaces.IBookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {

  IBookService service;
  @MockBean
  BookRepository repository;

  @MockBean
  ModelMapper mapper;

  @BeforeEach
  public void setup() {
    this.service = new BookService(repository, mapper);
  }

  @Test
  @DisplayName("Must create a book with success")
  public void createTest() {

    BookPostDto bookPostDto = BookPostDto
        .builder()
        .author("José de Assis")
        .title("Clean Code")
        .isbn("123456")
        .build();

    var bookEntity = BookEntity
        .builder()
        .author("José de Assis")
        .title("Clean Code")
        .isbn("123456")
        .build();

    Mockito.when(repository.save(bookEntity)).thenReturn(BookEntity
        .builder()
        .id(1l)
        .author("José de Assis")
        .title("Clean Code")
        .isbn("123456")
        .build());

    BookResponse bookResponse = service.create(bookPostDto);

    assertNotNull(bookResponse.getId());
    assertNotNull(bookResponse.getAuthor());
    assertNotNull(bookResponse.getIsbn());
    assertNotNull(bookResponse.getTitle());
    assertEquals(bookResponse.getTitle(), bookEntity.getTitle());
  }
}
