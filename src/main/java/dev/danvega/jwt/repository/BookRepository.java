package dev.danvega.jwt.repository;

import dev.danvega.jwt.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPublicationYearAfter(int year);

    List<Book> findByPublicationYearBefore(int year);


    List<Book> findByPublicationYearBetween(int startYear, int endYear);


    Optional<Book> findByIsbn(String isbn);


    void deleteByTitle(String title);




    long countByAuthor(String author);


}