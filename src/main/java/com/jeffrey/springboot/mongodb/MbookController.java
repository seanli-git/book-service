package com.jeffrey.springboot.mongodb;

import com.jeffrey.springboot.BookIdMismatchException;
import com.jeffrey.springboot.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/mbooks", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:8080")
public class MbookController {

    @Autowired
    private MbookRepository mbookRepository;

    @GetMapping
    public Iterable findAll() {
        return mbookRepository.findAll();
    }

    @GetMapping("/title/{bookTitle}")
    public List findByTitle(@PathVariable String bookTitle) {
        return mbookRepository.findByTitle(bookTitle);
    }

    @GetMapping("/{id}")
    public Mbook findOne(@PathVariable Long id) {
        return mbookRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mbook create(@RequestBody Mbook book) {
        return mbookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mbookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        mbookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Mbook updateBook(@RequestBody Mbook book, @PathVariable Long id) {
        if (book.getMyId() != id) {
            throw new BookIdMismatchException();
        }
        mbookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        return mbookRepository.save(book);
    }
}
