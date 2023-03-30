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
import org.springframework.data.mapping.PropertyReferenceException;
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
        if (bookRepository.existsById(id)) {
            return bookMapper.bookToBookDto(bookRepository.findById(id).orElse(null));
        } else {
            throw new RuntimeException("Book not found!");
        }
    }

    public List<BookDTO> getAllBooks(int page, String sort) {
        try {
            return bookRepository.findAll(PageRequest.of(page, 10, sort != null ? Sort.by(sort) : Sort.by("title")))
                    .stream()
                    .map(bookMapper::bookToBookDto)
                    .toList();
        } catch (PropertyReferenceException pre) {
            throw new RuntimeException("Sort parameter invalid!");
        }
    }

    public BookDTO addBook(BookAddRequestDTO requestDTO) {
        Book newBook = bookMapper.bookRequestDtoToBook(requestDTO);
        newBook.setId(UUID.randomUUID());
        bookRepository.save(newBook);
        return bookMapper.bookToBookDto(newBook);
    }

    public BookDTO editBook(BookEditRequestDTO requestDTO) {
        if (bookRepository.existsById(requestDTO.getId())) {
            Book editBook = bookMapper.bookEditRequestDtoToBook(requestDTO);
            bookRepository.save(editBook);
            return bookMapper.bookToBookDto(editBook);
        } else {
            throw new RuntimeException("Book not found!");
        }
    }

    public BookDeleteResponseDTO deleteBook(UUID id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return new BookDeleteResponseDTO("Book with ID " + id + " has been deleted!");
        } else {
            throw new RuntimeException("Book not found!");
        }
    }

    public BookDTO borrowBook(UUID id) {
        Book borrowBook = bookRepository.findById(id).orElse(null);
        if (borrowBook != null) {
            borrowBook.setStatus(BookStatus.BORROWED);
            bookRepository.save(borrowBook);
            return bookMapper.bookToBookDto(borrowBook);
        } else {
            throw new RuntimeException("Book not found!");
        }
    }

    public BookDTO returnBook(UUID id) {
        Book borrowBook = bookRepository.findById(id).orElse(null);
        if (borrowBook != null) {
            borrowBook.setStatus(BookStatus.FREE);
            bookRepository.save(borrowBook);
            return bookMapper.bookToBookDto(borrowBook);
        } else {
            throw new RuntimeException("Book not found!");
        }
    }
}
