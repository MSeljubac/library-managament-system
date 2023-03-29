package com.muamerseljubac.entity.dtos;

import com.muamerseljubac.entity.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;

    private String title;

    private AuthorDTO author;

    private CategoryDTO category;

    private BookStatus status;

}
