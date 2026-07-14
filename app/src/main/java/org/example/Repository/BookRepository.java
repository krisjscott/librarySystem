package org.example.Repository;

import org.example.Model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface BookRepository extends CrudRepository<Book, Long> {

    Optional<Book> findByBookName(String title);
    Optional<Book> findByAuthor(String author);

    boolean existsByName(String name);
}
