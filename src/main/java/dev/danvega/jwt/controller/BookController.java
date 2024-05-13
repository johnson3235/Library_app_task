package dev.danvega.jwt.controller;

import dev.danvega.jwt.dto.BookRequestDTO;
import dev.danvega.jwt.model.Book;
import dev.danvega.jwt.model.ResponseModel;
import dev.danvega.jwt.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping
    public ResponseEntity<ResponseModel<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        ResponseModel<Book> response = new ResponseModel<>(true, "Success", null, books);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return ResponseEntity.ok(new ResponseModel<>(true, "Success", null, List.of(book)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Book not found", null, null));
        }
    }


    @PostMapping
    public ResponseEntity<ResponseModel<Book>> addBook(@Valid @RequestBody BookRequestDTO bookDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest()
                    .body(new ResponseModel<>(false, "Validation error", errors, null));
        }

        Book savedBook = bookService.addBook(bookDto.toEntity());
        ResponseModel<Book> response = new ResponseModel<>(true, "Book added successfully", null, List.of(savedBook));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Book>> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        if (updatedBook != null) {
            return ResponseEntity.ok(new ResponseModel<>(true, "Book updated successfully", null, List.of(updatedBook)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Book not found", null, null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Void>> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.ok(new ResponseModel<>(true, "Book deleted successfully", null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Book not found", null, null));
        }
    }
}