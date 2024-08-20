package com.joskar.smartjob.test.validators.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordRegexValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordRegex {
    String message() default "Password no cumple con el patron requerido";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
