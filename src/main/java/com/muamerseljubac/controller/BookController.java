package com.muamerseljubac.controller;

import com.muamerseljubac.entity.dtos.BookDTO;
import com.muamerseljubac.entity.dtos.request.BookAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.BookEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.BookDeleteResponseDTO;
import com.muamerseljubac.entity.dtos.response.ErrorResponseDTO;
import com.muamerseljubac.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<BookDTO>> getAllBooks(@PathVariable("page") int page,
                                                     @RequestParam(value = "sort", required = false) String sort) {
        return new ResponseEntity<>(bookService.getAllBooks(page, sort), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookAddRequestDTO requestDTO) {
        return new ResponseEntity<>(bookService.addBook(requestDTO), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<BookDTO> editBook(@RequestBody BookEditRequestDTO requestDTO) {
        return new ResponseEntity<>(bookService.editBook(requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDeleteResponseDTO> deleteBook(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK);
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<BookDTO> borrowBook(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(bookService.borrowBook(id), HttpStatus.OK);
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<BookDTO> returnBook(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(bookService.returnBook(id), HttpStatus.OK);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
