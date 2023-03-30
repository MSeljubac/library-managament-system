package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.AuthorDTO;
import com.muamerseljubac.entity.models.Author;
import com.muamerseljubac.mapper.AuthorMapper;
import com.muamerseljubac.repository.AuthorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    public void getAuthorTest() throws Exception {
        UUID id = UUID.randomUUID();
        Author author = new Author(id, "John Doe");

        Mockito.when(authorRepository.existsById(id)).thenReturn(true);
        Mockito.when(authorRepository.findById(id)).thenReturn(Optional.of(author));

        AuthorDTO authorDTO = authorService.getAuthor(id);

        Assert.assertEquals(authorDTO, authorMapper.authorToAuthorDto(author));
    }

    @Test
    public void getAllAuthorsTest() {
        UUID id1 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        Author author1 = new Author(id1, "John Doe");
        Author author2 = new Author(id2, "Lily Finn");
        Author author3 = new Author(id3, "Jane Doe");

        Mockito.when(authorRepository.findAll(PageRequest.of(0, 10, Sort.by("title"))))
                .thenReturn(new PageImpl<>(Arrays.asList(author1, author2, author3)));

        List<AuthorDTO> books = authorService.getAllAuthors(0, "title");

        Assert.assertEquals(books, Stream.of(author1, author2, author3)
                .map(authorMapper::authorToAuthorDto).toList());
    }

}
