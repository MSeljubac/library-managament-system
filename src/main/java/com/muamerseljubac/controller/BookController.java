package com.muamerseljubac.controller;

import com.muamerseljubac.entity.dtos.BookDTO;
import com.muamerseljubac.entity.dtos.request.BookEditRequestDTO;
import com.muamerseljubac.entity.dtos.request.BookAddRequestDTO;
import com.muamerseljubac.entity.dtos.response.BookDeleteResponseDTO;
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

    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
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
}
