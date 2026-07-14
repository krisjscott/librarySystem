package org.example.Service;

import org.example.Dto.CreateBookRepositoryDto;
import org.example.ErrorHandling.BookIdMismatchException;
import org.example.ErrorHandling.BookNotFoundException;
import org.example.ErrorHandling.DuplicateResouceException;
import org.example.Model.Book;
import org.example.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Optional<Book> findByTitle(String title) {
        Optional<Book> found = bookRepository.findByBookName(title);
        if (found == null) {
            throw new BookNotFoundException("No book available for: " + title);
        }
        return found;
    }

    public Book findByAuthor(String author) {
        return bookRepository.findByAuthor(author)
                .orElseThrow(() -> new BookNotFoundException("No books by " + author));
    }

    public CreateBookRepositoryDto create(CreateBookRepositoryDto dto) {
        if (bookRepository.existsByName(dto.getName())) {
            throw new DuplicateResouceException("Book already exists with name: " + dto.getName());
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
        if (!id.equals(bk.getId())) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("No book found with id: " + id));
        return bookRepository.save(bk);
    }

    private Book mapToEntity(CreateBookRepositoryDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.createAt(LocalDateTime.now());
        return book;
    }

    private CreateBookRepositoryDto mapToDto(Book book) {
        return new CreateBookRepositoryDto(book.getId(), book.getName(), book.getAuthor());
    }
}
