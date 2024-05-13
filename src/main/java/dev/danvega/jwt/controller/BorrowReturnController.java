package dev.danvega.jwt.controller;

import dev.danvega.jwt.model.BorrowingRecord;
import dev.danvega.jwt.model.ResponseModel;
import dev.danvega.jwt.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BorrowReturnController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public ResponseEntity<ResponseModel<BorrowingRecord>> getAllBorrowedBooks() {
        List<BorrowingRecord> borrowedBooks = borrowingRecordService.getAllBorrowedBooks();
        return ResponseEntity.ok(new ResponseModel<>(true, "Book borrowed successfully", null, borrowedBooks));
    }
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseModel<Void>> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
       boolean saved =  borrowingRecordService.borrowBook(bookId, patronId);
       if(saved)
       {
           return ResponseEntity.ok(new ResponseModel<>(true, "Book borrowed successfully", null, null));

       }
       else
       {
           return ResponseEntity.ok(new ResponseModel<>(true, "Book Already borrowed and not Returned ", null, null));

       }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<ResponseModel<Void>> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        borrowingRecordService.returnBook(bookId, patronId);
        return ResponseEntity.ok(new ResponseModel<>(true, "Book returned successfully", null, null));
    }
}