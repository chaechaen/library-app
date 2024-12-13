package com.group.libraryapp.controller.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.dto.book.requestDTO.BookCreateRequestDTO;
import com.group.libraryapp.dto.book.requestDTO.BookLoanRequestDTO;
import com.group.libraryapp.dto.book.requestDTO.BookReturnRequestDTO;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveBook(@RequestBody BookCreateRequestDTO requestDTO) {
        bookService.saveBook(requestDTO);
    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody BookLoanRequestDTO requestDTO) {
        bookService.loanBook(requestDTO);
    }

    @PutMapping("/book/return")
    public void returnBook(@RequestBody BookReturnRequestDTO requestDTO) {
        bookService.returnBook(requestDTO);
    }
}
