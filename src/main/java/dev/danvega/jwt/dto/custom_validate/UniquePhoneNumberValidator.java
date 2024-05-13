package dev.danvega.jwt.dto.custom_validate;

import dev.danvega.jwt.repository.PatronRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

    @Autowired
    private PatronRepository patronRepository;

    private Long id;

    @Override
    public void initialize(UniquePhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true; // Null values are considered valid
        }
        return patronRepository.findByContactInformationAndIdNot(phoneNumber, id) == null;
    }
}