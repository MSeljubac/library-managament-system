package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.BookDTO;
import com.muamerseljubac.entity.dtos.request.BookAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.BookEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.BookDeleteResponseDTO;
import com.muamerseljubac.entity.enums.BookStatus;
import com.muamerseljubac.entity.models.Book;
import com.muamerseljubac.mapper.BookMapper;
import com.muamerseljubac.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<BookDTO> getAllBooks(int page, String sort) {
        return bookRepository.findAll(PageRequest.of(page, 10, sort != null ? Sort.by(sort) : Sort.by("title")))
                .stream()
                .map(bookMapper::bookToBookDto)
                .toList();
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

    public BookDTO borrowBook(UUID id) {
        Book borrowBook = bookRepository.findById(id).orElse(null);
        if (borrowBook != null) {
            borrowBook.setStatus(BookStatus.BORROWED);
            bookRepository.save(borrowBook);
            return bookMapper.bookToBookDto(borrowBook);
        } else {
            return null;
        }
    }

    public BookDTO returnBook(UUID id) {
        Book borrowBook = bookRepository.findById(id).orElse(null);
        if (borrowBook != null) {
            borrowBook.setStatus(BookStatus.FREE);
            bookRepository.save(borrowBook);
            return bookMapper.bookToBookDto(borrowBook);
        } else {
            return null;
        }
    }
}
