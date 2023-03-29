package com.muamerseljubac.entity.dtos;

import com.muamerseljubac.entity.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;

    private String title;

    private List<AuthorDTO> authors;

    private List<CategoryDTO> categories;

    private BookStatus status;

}
