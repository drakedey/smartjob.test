package com.joskar.smartjob.test.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joskar.smartjob.test.models.Phone;
import com.joskar.smartjob.test.models.User;
import com.joskar.smartjob.test.validators.custom.PasswordRegex;
import com.joskar.smartjob.test.validators.custom.UniqueEmail;
import com.joskar.smartjob.test.validators.handlers.InvalidParameterConstraintException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDTO {

    @NotEmpty(message = "El nombre del usuario es requerido")
    private String name;
    @NotEmpty(message = "El email del usuario es requerido")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.cl", message = "Debe ser un correo valido con dominio .cl")
    @UniqueEmail
    private String email;
    @PasswordRegex()
    private String password;
    private List<PhoneDTO> phones;

    @JsonIgnore
    public void isValid(Validator validator) throws InvalidParameterConstraintException {
        InvalidParameterConstraintException invalidRequestException =
                new InvalidParameterConstraintException(this.getClass(), validator.validate(this));
        if(!invalidRequestException.getErrors().isEmpty()) throw invalidRequestException;
        if(Objects.nonNull(phones) && !phones.isEmpty()) {
            this.phones.forEach(phone -> phone.isValid(validator));
        }
    }



    @JsonIgnore
    public User toEntity() {
        User user = new User();
        user.setName(this.name);
        user.setEmail(this.email);
        return user;
    }

    @JsonIgnore
    public List<Phone> getPhones(User user) {
        if(Objects.nonNull(this.phones) && !phones.isEmpty()) {
            return this.phones.stream().map(phone -> phone.toEntity(user)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}