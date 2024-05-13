package dev.danvega.jwt.dto.custom_validate;

import dev.danvega.jwt.repository.PatronRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniquePhoneNumberValidator2 implements ConstraintValidator<UniquePhoneNumber2, String> {

    @Autowired
    private PatronRepository patronRepository;

    @Override
    public void initialize(UniquePhoneNumber2 constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true; // Null values are considered valid
        }
        return patronRepository.findByContactInformation(phoneNumber) == null;
    }
}
