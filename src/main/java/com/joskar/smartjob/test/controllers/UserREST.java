package com.joskar.smartjob.test.controllers;

import com.joskar.smartjob.test.controllers.dto.CreateUserRequestDTO;
import com.joskar.smartjob.test.controllers.dto.CreateUserResponseDTO;
import com.joskar.smartjob.test.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserREST {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "API para crear un usuario y asignarle telefonos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado satisfactoramente"),
            @ApiResponse(responseCode = "400", description = "Error de validacion en el DTO o duplicidad de datos")
    })
    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return ResponseEntity.ok(userService.createUser(createUserRequestDTO));
    }
}
