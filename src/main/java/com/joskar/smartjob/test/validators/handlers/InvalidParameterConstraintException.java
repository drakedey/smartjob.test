package com.joskar.smartjob.test.validators.handlers;

import jakarta.validation.ConstraintViolation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class InvalidParameterConstraintException extends RuntimeException {
    private Map<String, String> errors;

    public<T> InvalidParameterConstraintException(Class<T> classT, Set<ConstraintViolation<Object>> violations) {
        super("Error en la validacion de la clase: " + classT.getName());
        this.errors = new HashMap<>();
        violations.forEach(violation -> errors.put(violation.getPropertyPath().toString(), violation.getMessage()));
    }
}
