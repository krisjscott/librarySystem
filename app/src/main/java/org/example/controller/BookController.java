package org.example.controller;

import org.example.ErrorHandling.UserNotFoundException;
import org.example.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.example.ErrorHandling.BookNotFoundException;
import org.example.ErrorHandling.BookIdMismatchException;


import org.example.Model.book;

import java.util.Collections;
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
    public book findByTitle(@PathVariable String bookTitle) {

        if(bookRepository.findByTitle(bookTitle) == null || bookRepository.findByName(bookTitle) == null) {
            throw new BookNotFoundException("No Book available for: "+ bookTitle);
        }
        return bookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/author/{author}") //add exception for this too
    public List findByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    @GetMapping("/{id}")
    public book findById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("No book found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public book create(@RequestBody book bk) {
        return bookRepository.save(bk);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException("Book was never created with id: "+id));
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public book updateBook(@RequestBody book bk, @PathVariable Long id) {
        if(!id.equals(bk.getId())) {
            throw new BookIdMismatchException();
        }
        bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException("No book was found"));
        return bookRepository.save(bk);
    }
}
