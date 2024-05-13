package dev.danvega.jwt.repository;

import dev.danvega.jwt.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {

    List<Patron> findByName(String title);
    Patron findByContactInformation(String contactInformation);

    Patron findByContactInformationAndIdNot(String contactInformation, Long id);


}