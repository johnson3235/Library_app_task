package dev.danvega.jwt.dto;


import dev.danvega.jwt.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BookRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotNull(message = "Publication year is required")
    @Pattern(regexp = "\\b(19|20)\\d{2}\\b", message = "Invalid publication year")
    private String publicationYear;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    // Default constructor
    public BookRequestDTO() {}

    // Constructor with all fields
    public BookRequestDTO(String title, String author, String publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "BookRequestDTO{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }


    // Convert this DTO to an entity
    public Book toEntity() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setPublicationYear(Integer.parseInt(this.publicationYear));
        book.setIsbn(this.isbn);
        return book;
    }
    // Constructors, getters, and setters
}