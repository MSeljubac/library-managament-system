package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.AuthorDTO;
import com.muamerseljubac.entity.dtos.request.AuthorAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.AuthorEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.AuthorDeleteResponseDTO;
import com.muamerseljubac.entity.models.Author;
import com.muamerseljubac.mapper.AuthorMapper;
import com.muamerseljubac.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDTO addAuthor(AuthorAddRequestDTO requestDTO) {
        Author author = authorMapper.authorAddRequestDtoToAuthor(requestDTO);
        author.setId(UUID.randomUUID());
        authorRepository.save(author);
        return authorMapper.authorToAuthorDto(author);
    }

    public AuthorDTO getAuthor(UUID id) {
        return authorMapper.authorToAuthorDto(authorRepository.findById(id).orElse(null));
    }

    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorMapper::authorToAuthorDto).toList();
    }

    public AuthorDTO editAuthor(AuthorEditRequestDTO requestDTO) {
        Author author = authorMapper.authorEditRequestDtoToAuthor(requestDTO);
        authorRepository.save(author);
        return authorMapper.authorToAuthorDto(author);
    }

    public AuthorDeleteResponseDTO deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
        return new AuthorDeleteResponseDTO("Author with ID " + id + " successfully deleted!");
    }
}
