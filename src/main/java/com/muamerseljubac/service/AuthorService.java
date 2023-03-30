package com.muamerseljubac.service;

import com.muamerseljubac.entity.dtos.AuthorDTO;
import com.muamerseljubac.entity.dtos.request.AuthorAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.AuthorEditRequestDTO;
import com.muamerseljubac.entity.dtos.response.AuthorDeleteResponseDTO;
import com.muamerseljubac.entity.models.Author;
import com.muamerseljubac.mapper.AuthorMapper;
import com.muamerseljubac.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
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
        if (authorRepository.existsById(id)) {
            return authorMapper.authorToAuthorDto(authorRepository.findById(id).orElse(null));
        } else {
            throw new RuntimeException("Author not found!");
        }
    }

    public List<AuthorDTO> getAllAuthors(int page, String sort) {
        try {
            return authorRepository.findAll(PageRequest.of(page, 10, sort != null ? Sort.by(sort) : Sort.by("name")))
                    .stream()
                    .map(authorMapper::authorToAuthorDto)
                    .toList();
        } catch (PropertyReferenceException pre) {
            throw new RuntimeException("Sort parameter invalid!");
        }
    }

    public AuthorDTO editAuthor(AuthorEditRequestDTO requestDTO) {
        if (authorRepository.existsById(requestDTO.getId())) {
            Author author = authorMapper.authorEditRequestDtoToAuthor(requestDTO);
            authorRepository.save(author);
            return authorMapper.authorToAuthorDto(author);
        } else {
            throw new RuntimeException("Author not found!");
        }
    }

    public AuthorDeleteResponseDTO deleteAuthor(UUID id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return new AuthorDeleteResponseDTO("Author with ID " + id + " successfully deleted!");
        } else {
            throw new RuntimeException("Author not found!");
        }
    }
}
