package com.galvanize.tmo.paspringstarter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class LibraryController {

    private ArrayList<Book> library = new ArrayList<Book>();

    @GetMapping("/health")
    public void health() {
    }

    @GetMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBooks() {
        System.out.println(library.toString());
        return new ResponseEntity<Library>(new Library(library), HttpStatus.OK);
    }

    @PostMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addToLibrary(@RequestBody Book book) {

        Book addedBook = new Book(library.size() + 1, book.getAuthor(), book.getTitle(), book.getYearPublished());

        for(int i = 0; i < library.size(); i++) {
            if(library.get(i).getTitle().compareTo(book.getTitle()) > 0) {
                library.add(i, addedBook);
                break;
            }
        }

        if(!library.contains(addedBook)) {
            library.add(addedBook);
        }

        if(library.size() > 0) {
            return new ResponseEntity<Book>(addedBook, HttpStatus.CREATED);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
    }

    @DeleteMapping("/api/books")
    public ResponseEntity deleteLibrary() {

        library.clear();

        if(library.isEmpty()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
    }
}
