package com.joskar.smartjob.test.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joskar.smartjob.test.models.Phone;
import com.joskar.smartjob.test.models.User;
import com.joskar.smartjob.test.validators.handlers.InvalidParameterConstraintException;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
    @NotEmpty(message = "El numero de telefono es requerido")
    private String number;
    @NotEmpty(message = "El codigo de ciudad es requerido")
    private String cityCode;
    @NotEmpty(message = "El codigo de pais es requerido")
    private String countryCode;

    public void isValid(Validator validator) throws InvalidParameterConstraintException {
        InvalidParameterConstraintException invalidRequestException = new InvalidParameterConstraintException(this.getClass(), validator.validate(this));
        if(!invalidRequestException.getErrors().isEmpty()) throw invalidRequestException;
    }

    @JsonIgnore
    public Phone toEntity(User user) {
        Phone phone = new Phone();
        phone.setCityCode(this.cityCode);
        phone.setCountryCode(this.countryCode);
        phone.setPhoneNumber(this.number);
        phone.setUser(user);
        return phone;
    }
}
