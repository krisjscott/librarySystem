package org.example.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.example.Model.book;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<book, Long> {

    List<book> findByName(String name);
    List<book> findByAuthor(String author);

    @Query("SELECT b FROM book b WHERE b.name = :title")
    book findByTitle(@Param("title") String title);
}
