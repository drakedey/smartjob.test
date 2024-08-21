package com.joskar.smartjob.test.validators.advices;

import com.joskar.smartjob.test.validators.handlers.InvalidParameterConstraintException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(InvalidParameterConstraintException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomErrorDto> handleInvalidParametersConstraintException(InvalidParameterConstraintException ex) {
        CustomErrorDto customErrorDto = new CustomErrorDto();
        customErrorDto.setErrors(ex.getErrors());
        customErrorDto.setMessage(ex.getMessage());
        customErrorDto.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(customErrorDto);
    }
}

@Getter
@Setter
@NoArgsConstructor
class CustomErrorDto {
    private Map<String, String> errors;
    private String message;
    private Integer status;
}
