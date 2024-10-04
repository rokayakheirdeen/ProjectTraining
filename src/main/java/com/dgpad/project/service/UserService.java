package com.dgpad.project.service;

import com.dgpad.project.DTO.response.request.UserRegistrationDto;
import com.dgpad.project.model.User;
import com.dgpad.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDto registrationDto, String role) {
        if (userRepository.findByUsername(registrationDto.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.findByEmail(registrationDto.getEmail()) != null) {
            throw new RuntimeException("Email already taken");
        }

        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(User.Role.valueOf(role.toUpperCase()));
        userRepository.save(user);
    }
}