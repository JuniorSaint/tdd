package com.library.loan.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;

public class ApiErros {
  private List<String> erros;

  public ApiErros(BindingResult bindingResult) {
    this.erros = new ArrayList<>();
    bindingResult.getAllErrors().forEach(error -> this.erros.add(error.getDefaultMessage()));
  }
}
