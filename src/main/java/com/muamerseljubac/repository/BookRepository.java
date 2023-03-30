package com.muamerseljubac.repository;

import com.muamerseljubac.entity.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findAll(Pageable pageable);

}
