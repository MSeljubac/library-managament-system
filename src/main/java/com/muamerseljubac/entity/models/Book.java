package com.muamerseljubac.entity.models;

import com.muamerseljubac.entity.enums.BookStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    private BookStatus status;

}
