package com.muamerseljubac.entity.models;

import com.muamerseljubac.entity.enums.BookStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    private UUID id;

    private String title;

    @OneToMany
    private List<Author> authors;

    @OneToMany
    private List<Category> categories;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

}
