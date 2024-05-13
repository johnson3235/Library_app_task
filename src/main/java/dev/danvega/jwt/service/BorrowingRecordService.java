package dev.danvega.jwt.service;

import dev.danvega.jwt.model.Book;
import dev.danvega.jwt.model.BorrowingRecord;
import dev.danvega.jwt.model.Patron;
import dev.danvega.jwt.repository.BookRepository;
import dev.danvega.jwt.repository.BorrowingRecordRepository;
import dev.danvega.jwt.repository.PatronRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    public List<BorrowingRecord> getAllBorrowedBooks() {
        return borrowingRecordRepository.findAll();
    }
    public boolean borrowBook(Long bookId, Long patronId) {
        // Check if both the book and patron exist
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + bookId));
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new EntityNotFoundException("Patron not found with ID: " + patronId));

        Optional<BorrowingRecord> optionalRecord = borrowingRecordRepository.findByBookIdAndReturnDateIsNull(bookId);

        if (optionalRecord.isPresent()) {
            return false; // The book is already borrowed
        }

        try {
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowingDate(LocalDate.now());

            borrowingRecordRepository.save(borrowingRecord);
        }
        catch (Exception e)
        {
            throw e;
        }

        return true;
    }

    public void returnBook(Long bookId, Long patronId) {
        // Find the borrowing record by book ID and patron ID
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
        if (borrowingRecord == null) {
            throw new EntityNotFoundException("Borrowing record not found for book ID: " + bookId + " and patron ID: " + patronId);
        }

        // Set the return date to today
        borrowingRecord.setReturnDate(LocalDate.now());

        // Save the updated borrowing record
        borrowingRecordRepository.save(borrowingRecord);
    }
}