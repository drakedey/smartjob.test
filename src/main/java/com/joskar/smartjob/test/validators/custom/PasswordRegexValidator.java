package com.joskar.smartjob.test.validators.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PasswordRegexValidator implements ConstraintValidator<PasswordRegex, String> {
    @Value("${smartjob.test.password-regex:^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$}")
    private String passwordRegex;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return isValidPassword(password);
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(this.passwordRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
