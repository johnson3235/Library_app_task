package dev.danvega.jwt.repository;

import dev.danvega.jwt.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {


    List<BorrowingRecord> findByBookId(Long bookId);

    List<BorrowingRecord> findByPatronId(Long patronId);

    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);

    Optional<BorrowingRecord> findByBookIdAndReturnDateIsNull(Long bookId);
    BorrowingRecord findByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);

}