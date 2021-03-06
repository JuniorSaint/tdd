package com.library.loan.models.dtos.books;

import java.util.UUID;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {

  private Long id;  

  private String title;

  private String author;

  private String isbn;
  
}
