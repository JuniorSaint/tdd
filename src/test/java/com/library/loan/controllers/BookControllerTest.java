package com.library.loan.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.loan.models.dtos.books.BookPostDto;
import com.library.loan.models.dtos.books.BookResponse;
import com.library.loan.services.interfaces.IBookService;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mvc;

    static String BOOK_API = "http://localhost/api/books";

    @MockBean
    IBookService service;

    @Test
    @DisplayName("Must create a book with success")
    public void createBookTest() throws Exception {

        BookPostDto bookPost = BookPostDto
                .builder()
                .author("Jose Atanazio")
                .isbn("12346")
                .title("My Book")
                .build();

        BookResponse bookResponse = BookResponse
                .builder()
                .author("Jose Atanazio")
                .isbn("12346")
                .id(1l)
                .title("My Book")
                .build();

        BDDMockito.given(service.create(Mockito.any(BookPostDto.class))).willReturn(bookResponse);

        String json = new ObjectMapper().writeValueAsString(bookPost);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("title").value(bookResponse.getTitle()))
                .andExpect(jsonPath("author").value(bookResponse.getAuthor()))
                .andExpect(jsonPath("isbn").value(bookResponse.getIsbn()));
    }

    @Test
    @DisplayName("Must create a book with error")
    public void createBookErroTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new BookPostDto());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
                // .andExpect(jsonPath("error", Matchers.hasSize(3)));
    }

    @Test
    @DisplayName("Delete book with success")
    public void deleteBookTest() throws Exception{
            
    }

}
