package org.example.Repository;

import org.example.Model.Book;
import org.springframework.data.repository.CrudRepository;;


public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByTitle(String title);
    Book findByAuthor(String author);

    boolean existsByTitle(String title);
}
