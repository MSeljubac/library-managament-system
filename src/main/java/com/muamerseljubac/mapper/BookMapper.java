package com.muamerseljubac.mapper;

import com.muamerseljubac.entity.dtos.BookDTO;
import com.muamerseljubac.entity.dtos.request.BookAddRequestDTO;
import com.muamerseljubac.entity.dtos.request.BookEditRequestDTO;
import com.muamerseljubac.entity.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDto(Book book);

    Book bookRequestDtoToBook(BookAddRequestDTO bookAddRequestDTO);

    Book bookEditRequestDtoToBook(BookEditRequestDTO bookEditRequestDTO);

}
