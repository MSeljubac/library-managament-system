package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.BookDTO;
import com.muamerseljubac.entity.dtos.request.BookAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.BookEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.BookDeleteResponseDTO;
import com.muamerseljubac.entity.models.Book;
import com.muamerseljubac.mapper.BookMapper;
import com.muamerseljubac.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDTO getBook(UUID id) {
        return bookMapper.bookToBookDto(bookRepository.findById(id).orElse(null));
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(bookMapper::bookToBookDto).toList();
    }

    public BookDTO addBook(BookAddRequestDTO requestDTO) {
        Book newBook = bookMapper.bookRequestDtoToBook(requestDTO);
        newBook.setId(UUID.randomUUID());
        bookRepository.save(newBook);
        return bookMapper.bookToBookDto(newBook);
    }

    public BookDTO editBook(BookEditRequestDTO requestDTO) {
        Book editBook = bookMapper.bookEditRequestDtoToBook(requestDTO);
        bookRepository.save(editBook);
        return bookMapper.bookToBookDto(editBook);
    }

    public BookDeleteResponseDTO deleteBook(UUID id) {
        bookRepository.deleteById(id);
        return new BookDeleteResponseDTO("Book with ID " + id + " has been deleted!");
    }
}
