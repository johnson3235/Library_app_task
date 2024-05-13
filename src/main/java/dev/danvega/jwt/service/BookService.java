package dev.danvega.jwt.service;

import dev.danvega.jwt.model.Book;
import dev.danvega.jwt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        // Check if the book with the given id exists
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id); // Make sure the ID is set correctly
            return bookRepository.save(updatedBook);
        } else {
            throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
        }
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}