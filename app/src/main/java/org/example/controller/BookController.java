package org.example.controller;

import org.example.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.example.ErrorHandling.BookNotFoundException;
import org.example.ErrorHandling.BookIdMismatchException;


import org.example.Model.book;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public Iterable findAll() {
        return bookRepository.findAll();
    }
    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return bookRepository.findByName(bookTitle);
    }
    @GetMapping("/author/{author}")
    public List findByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }
    @GetMapping("/{id}")
    public book findById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public book create(@RequestBody book bk) {
        return bookRepository.save(bk);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public book updateBook(@RequestBody book bk, @PathVariable Long id) {
        if(!id.equals(bk.getId())) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return bookRepository.save(bk);
    }
}
