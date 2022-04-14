package com.library.loan.models.dtos.books;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPostDto {
  

  @NotEmpty
  private String title;

  @NotEmpty
  private String author;

  @NotEmpty
  private String isbn;

}
