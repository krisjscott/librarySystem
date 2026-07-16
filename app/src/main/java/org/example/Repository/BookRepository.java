package org.example.Repository;

import org.example.Entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByTitle(String title);
    Book findByAuthor(String author);

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.title = :title AND b.issuedBy = true")
    boolean ifIssuedOrNot(@Param("title") String title);

    boolean existsByTitle(String title);
}
