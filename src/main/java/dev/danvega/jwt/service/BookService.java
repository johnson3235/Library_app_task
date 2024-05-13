package dev.danvega.jwt.service;

import dev.danvega.jwt.model.Book;
import dev.danvega.jwt.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "books", key = "#id")
    public Optional<Book> getBookById(Long id) {
        // Simulate a slow database call
        return findBookInDatabase(id);
    }
    private Optional<Book> findBookInDatabase(Long id) {
        return Optional.ofNullable(bookRepository.findById(id).orElse(null));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById2(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    @Transactional
    @CacheEvict(value = "books", key = "#id")
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
//        bookRepository.deleteById(id);
    }

    @Transactional
    @CachePut(value = "books", key = "#book.id")
    public Book updateBook(Long id,Book updatedBook) {
        if (bookRepository.existsById(id)) {
            updatedBook.setId(id);
            return bookRepository.save(updatedBook);
        } else {
            throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
        }
    }


//    public Book updateBook(Long id, Book updatedBook) {
//        // Check if the book with the given id exists
//        if (bookRepository.existsById(id)) {
//            updatedBook.setId(id); // Make sure the ID is set correctly
//            return bookRepository.save(updatedBook);
//        } else {
//            throw new IllegalArgumentException("Book with ID " + id + " does not exist.");
//        }
//    }

//    public void deleteBook(Long id) {
//        bookRepository.deleteById(id);
//    }
}