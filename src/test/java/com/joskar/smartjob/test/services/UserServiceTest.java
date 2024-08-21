package com.joskar.smartjob.test.services;

import com.joskar.smartjob.test.controllers.dto.CreateUserRequestDTO;
import com.joskar.smartjob.test.controllers.dto.CreateUserResponseDTO;
import com.joskar.smartjob.test.controllers.dto.PhoneDTO;
import com.joskar.smartjob.test.validators.handlers.InvalidParameterConstraintException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void createUser_shouldReturnCreateUserResponseDTO() {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(new PhoneDTO("12345", "1", "57"));
        CreateUserRequestDTO createUserRequestDTO =
                new CreateUserRequestDTO("Joskar", "joskar.test@domain.cl", "TestingValid1", phones);

        CreateUserResponseDTO response = userService.createUser(createUserRequestDTO);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getToken());
        assertNotNull(response.getCreated());
        assertNotNull(response.getModified());
        assertNotNull(response.getLastLogin());
        assertNotNull(response.getIsActive());
        assertEquals(response.getIsActive(), true);
    }

    @Test
    public void createUser_shouldThrowException_whenEmailIsDuplicated() {
        final String EMAIL_ERROR = "joskar.common@domain.cl";
        CreateUserRequestDTO createUserRequestDTO =
                new CreateUserRequestDTO("Joskar", EMAIL_ERROR, "TestingValid1", new ArrayList<>());

        userService.createUser(createUserRequestDTO);
        InvalidParameterConstraintException ex = assertThrows(InvalidParameterConstraintException.class, () -> {
            userService.createUser(createUserRequestDTO);
        });

        assertTrue(ex.getMessage().contains(CreateUserRequestDTO.class.getName()));
        assertTrue(ex.getErrors().containsKey("email"));
        assertEquals("El correo ya ha sido registrado", ex.getErrors().get("email"));
    }

    @Test
    public void createUser_shouldThrowException_whenEmailIsInvalid() {
        final String EMAIL_ERROR = "joskar.common@domain.com";
        CreateUserRequestDTO createUserRequestDTO =
                new CreateUserRequestDTO("Joskar", EMAIL_ERROR, "TestingValid1", new ArrayList<>());

        InvalidParameterConstraintException ex = assertThrows(InvalidParameterConstraintException.class, () -> {
            userService.createUser(createUserRequestDTO);
        });

        assertTrue(ex.getMessage().contains(CreateUserRequestDTO.class.getName()));
        assertTrue(ex.getErrors().containsKey("email"));
        assertEquals("Debe ser un correo valido con dominio .cl", ex.getErrors().get("email"));
    }

    @Test
    public void createUser_shouldThrowException_whenFieldsAreNotSet() {
        CreateUserRequestDTO createUserRequestDTO =
                new CreateUserRequestDTO(null, null, null, null);

        InvalidParameterConstraintException ex = assertThrows(InvalidParameterConstraintException.class, () -> {
            userService.createUser(createUserRequestDTO);
        });

        assertTrue(ex.getMessage().contains(CreateUserRequestDTO.class.getName()));
        assertTrue(ex.getErrors().containsKey("email"));
        assertTrue(ex.getErrors().containsKey("name"));
        assertTrue(ex.getErrors().containsKey("password"));
        assertEquals("El nombre del usuario es requerido", ex.getErrors().get("name"));
        assertEquals("El email del usuario es requerido", ex.getErrors().get("email"));
        assertEquals("Password no cumple con el patron requerido", ex.getErrors().get("password"));
    }

    @Test
    public void createUser_shouldThrowException_whenPhoneFieldsAreNotSet() {
        List<PhoneDTO> phones = new ArrayList<>();
        phones.add(new PhoneDTO(null, null, null));
        CreateUserRequestDTO createUserRequestDTO =
                new CreateUserRequestDTO("Joskar", "joskar.test@domain.cl", "TestingValid1", phones);

        InvalidParameterConstraintException ex = assertThrows(InvalidParameterConstraintException.class, () -> {
            userService.createUser(createUserRequestDTO);
        });

        assertTrue(ex.getMessage().contains(PhoneDTO.class.getName()));
        assertTrue(ex.getErrors().containsKey("number"));
        assertTrue(ex.getErrors().containsKey("cityCode"));
        assertTrue(ex.getErrors().containsKey("countryCode"));
        assertEquals("El numero de telefono es requerido", ex.getErrors().get("number"));
        assertEquals("El codigo de ciudad es requerido", ex.getErrors().get("cityCode"));
        assertEquals("El codigo de pais es requerido", ex.getErrors().get("countryCode"));
    }

}
