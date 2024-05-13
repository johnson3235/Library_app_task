package dev.danvega.jwt.dto.custom_validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePhoneNumberValidator.class)
@Documented
public @interface UniquePhoneNumber {

    String message() default "Phone number already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}