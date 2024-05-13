package dev.danvega.jwt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class BorrowingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotNull(message = "Book is required")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    @NotNull(message = "Patron is required")
    private Patron patron;

    @NotNull(message = "Borrowing date is required")
//    @ValidDateFormat(message = "Invalid date format")
    private LocalDate borrowingDate;

//    @Past(message = "Return date must be in the past")
    private LocalDate returnDate;

    // Constructors, getters, and setters
    public BorrowingRecord() {
        this.borrowingDate = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}