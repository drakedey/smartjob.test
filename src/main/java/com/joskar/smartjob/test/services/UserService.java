package com.joskar.smartjob.test.services;

import com.joskar.smartjob.test.controllers.dto.CreateUserRequestDTO;
import com.joskar.smartjob.test.controllers.dto.CreateUserResponseDTO;
import com.joskar.smartjob.test.models.Phone;
import com.joskar.smartjob.test.models.User;
import com.joskar.smartjob.test.repository.PhoneRepository;
import com.joskar.smartjob.test.repository.UserRepository;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private Validator validator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public CreateUserResponseDTO createUser(CreateUserRequestDTO createUserRequestDTO) {
        createUserRequestDTO.isValid(validator);
        User userToCreate = createUserRequestDTO.toEntity();
        userToCreate.setPassword(passwordEncoder.encode(createUserRequestDTO.getPassword()));
        userToCreate.setToken(jwtService.generateToken(userToCreate));
        User persistedUser = userRepository.save(userToCreate);

        List<Phone> phones = createUserRequestDTO.getPhones(persistedUser);
        if(!phones.isEmpty()) {
            phoneRepository.saveAllAndFlush(phones);
        }

        return new CreateUserResponseDTO(persistedUser);
    }

}
