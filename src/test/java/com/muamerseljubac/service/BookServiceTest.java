package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.BookDTO;
import com.muamerseljubac.entity.enums.BookStatus;
import com.muamerseljubac.entity.models.Book;
import com.muamerseljubac.mapper.BookMapper;
import com.muamerseljubac.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void getBookTest() throws Exception {
        UUID id = UUID.randomUUID();
        Book book = new Book(id, "Thriller 1", new ArrayList<>(), new ArrayList<>(), BookStatus.FREE);

        Mockito.when(bookRepository.existsById(id)).thenReturn(true);
        Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        BookDTO bookDTO = bookService.getBook(id);

        Assert.assertEquals(bookDTO, bookMapper.bookToBookDto(book));
    }

    @Test
    public void getAllBooksTest() {
        UUID id1 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Book book1 = new Book(id1, "Thriller 1", new ArrayList<>(), new ArrayList<>(), BookStatus.FREE);
        Book book2 = new Book(id2, "Thriller 2", new ArrayList<>(), new ArrayList<>(), BookStatus.FREE);
        Book book3 = new Book(id3, "Thriller 3", new ArrayList<>(), new ArrayList<>(), BookStatus.FREE);

        Mockito.when(bookRepository.findAll(PageRequest.of(0, 10, Sort.by("title"))))
                .thenReturn(new PageImpl<>(Arrays.asList(book1, book2, book3)));

        List<BookDTO> books = bookService.getAllBooks(0, "title");

        Assert.assertEquals(books, Stream.of(book1, book2, book3).map(bookMapper::bookToBookDto).toList());
    }

}
