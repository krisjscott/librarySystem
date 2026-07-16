package org.example.Service;

import org.example.Dto.CreateBookRepositoryDto;
import org.example.ErrorHandling.BookIdMissingException;
import org.example.ErrorHandling.BookNotFoundException;
import org.example.ErrorHandling.DuplicateResourceException;
import org.example.Entity.Book;
import org.example.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book found with id: " + id));
    }

    public Boolean ifIssued(String title){
        return bookRepository.ifIssuedOrNot(title);
    }

    public Book findBookByAuthor(String author) {
        Book value = bookRepository.findByAuthor(author);
        if(value == null ) {
            throw new BookIdMissingException("Value is missing");
        }
        else {
            return value;
        }
    }

    public CreateBookRepositoryDto create(CreateBookRepositoryDto dto) {
        if (bookRepository.existsByTitle(dto.getTitle())) {
            throw new DuplicateResourceException("Book already exists with name: " + dto.getTitle());
        }
        Book saved = bookRepository.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    public void delete(Long id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book was never created with id: " + id));
        bookRepository.deleteById(id);
    }

    public Book update(Long id, Book bk) {
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book found with id: " + id));
        return bookRepository.save(bk);
    }

    private Book mapToEntity(CreateBookRepositoryDto dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIssuedBy(dto.isIssuedBy());
        book.createAt(LocalDateTime.now());
        return book;
    }

    public Book findByTitle(String title) {
        Book found = bookRepository.findByTitle(title);
        if (found == null) {
            throw new BookNotFoundException("No book available for: " + title);
        }
        return found;
    }

    private CreateBookRepositoryDto mapToDto(Book book) {
        return new CreateBookRepositoryDto(book.getId(), book.getTitle(), book.getAuthor(), book.getIssuedBy());
    }
}
