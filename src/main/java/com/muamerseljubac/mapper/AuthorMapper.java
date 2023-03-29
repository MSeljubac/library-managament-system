package com.muamerseljubac.mapper;

import com.muamerseljubac.entity.dtos.AuthorDTO;
import com.muamerseljubac.entity.dtos.request.AuthorAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.AuthorEditRequestDTO;
import com.muamerseljubac.entity.models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author authorAddRequestDtoToAuthor(AuthorAddRequestDTO requestDTO);

    @Mapping(source = "name", target = "name")
    AuthorDTO authorToAuthorDto(Author author);

    Author authorEditRequestDtoToAuthor(AuthorEditRequestDTO requestDTO);
}
