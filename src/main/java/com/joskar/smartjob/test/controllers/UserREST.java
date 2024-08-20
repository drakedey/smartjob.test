package com.joskar.smartjob.test.controllers;

import com.joskar.smartjob.test.controllers.dto.CreateUserRequestDTO;
import com.joskar.smartjob.test.controllers.dto.CreateUserResponseDTO;
import com.joskar.smartjob.test.services.UserService;
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

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        return ResponseEntity.ok(userService.createUser(createUserRequestDTO));
    }
}
