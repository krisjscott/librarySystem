package org.example.Repository;

import org.springframework.data.repository.CrudRepository;

import org.example.Model.book;
import java.util.List;

public interface BookRepository extends CrudRepository<book, Long> {
    List<book> findByName(String name);
    List<book> findByAuthor(String author);
}
