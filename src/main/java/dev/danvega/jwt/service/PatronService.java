package dev.danvega.jwt.service;

import dev.danvega.jwt.model.Patron;
import dev.danvega.jwt.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    public Patron addPatron(Patron Patron) {
        String contactInformation = Patron.getContactInformation();

        Patron existingEntity = patronRepository.findByContactInformation(contactInformation);
        if (existingEntity != null) {
          return  null;
        }
        else {
            return patronRepository.save(Patron);
        }
    }

    public Optional<Boolean> CheckIfContactInformationUnique(String ContactInformation, Long id)
    {
        if (patronRepository.existsById(id)) {
            Patron existingPatronWithUpdatedContactInfo = patronRepository.findByContactInformationAndIdNot(ContactInformation, id);
            if (existingPatronWithUpdatedContactInfo != null) {
                return Optional.of(false);
            }
            return Optional.of(true);
        }
        else {
            return null;
        }
    }

    public Patron updatePatron(Long id, Patron updatedPatron) {


        if (patronRepository.existsById(id)) {
            updatedPatron.setId(id);
            return patronRepository.save(updatedPatron);
        } else {
           return null;
        }
    }

    public boolean deletePatron(Long id) {

        if (patronRepository.existsById(id)) {
            patronRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }
}