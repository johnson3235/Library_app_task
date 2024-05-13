package dev.danvega.jwt.controller;

import dev.danvega.jwt.model.Patron;
import dev.danvega.jwt.model.ResponseModel;
import dev.danvega.jwt.service.PatronService;
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
@RequestMapping("/api/patrons")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public ResponseEntity<ResponseModel<Patron>> getAllPatrons() {
        List<Patron> Patrons = patronService.getAllPatrons();
        ResponseModel<Patron> response = new ResponseModel<>(true, "Success", null, Patrons);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseModel<Patron>> getPatronById(@PathVariable Long id) {
        Optional<Patron> PatronOptional = patronService.getPatronById(id);
        if (PatronOptional.isPresent()) {
            Patron Patron = PatronOptional.get();
            return ResponseEntity.ok(new ResponseModel<>(true, "Success", null, List.of(Patron)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Patron not found", null, null));
        }
    }


    @PostMapping
    public ResponseEntity<ResponseModel<Patron>> addPatron(@Validated @RequestBody Patron Patron, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest()
                    .body(new ResponseModel<>(false, "Validation error", errors, null));
        }

        Patron savedPatron = patronService.addPatron(Patron);
        ResponseModel<Patron> response;
        if(savedPatron != null)
        {
             response = new ResponseModel<>(true, "Patron added successfully", null, List.of(savedPatron));

        }
        else
        {
             response = new ResponseModel<>(false, "Contact Information Should be Unique", null, null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel<Patron>> updatePatron(@PathVariable Long id, @RequestBody Patron Patron) {
        Optional<Boolean> unique = patronService.CheckIfContactInformationUnique(Patron.getContactInformation(),id);
//        System.out.println(unique);
        if(unique == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Patron not found", null, null));
        }
        else{
        if(unique == Optional.of(true))
        {
            Patron updatedPatron = patronService.updatePatron(id, Patron);
            if (updatedPatron != null) {
                return ResponseEntity.ok(new ResponseModel<>(true, "Patron updated successfully", null, List.of(updatedPatron)));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Patron not found", null, null));
            }
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Contact Information must be Unique", null, null));
        }}

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseModel<Void>> deletePatron(@PathVariable Long id) {
            try {
               boolean deleted =  patronService.deletePatron(id);
                if(deleted)
                {
                    return ResponseEntity.ok(new ResponseModel<>(true, "Patron deleted successfully", null, null));
                }
                else
                {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Patron not found", null, null));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel<>(false, "Patron not found", null, null));
            }
    }
}