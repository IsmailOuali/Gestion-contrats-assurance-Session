package com.example.demo.service.Impl;

import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserCreateDTO userCreateDTO) {
        Optional<User> existingUser = userRepository.findByUsername(userCreateDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setEmail(userCreateDTO.getEmail());

        Role role = new Role();
        role.setName(userCreateDTO.getRole());
        user.setRoles(Set.of(role));

        User savedUser = userRepository.save(user);

        return mapToResponseDTO(savedUser);
    }

    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        return mapToResponseDTO(user);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(
                user.getRoles().stream().map(Role::getName).collect(Collectors.joining(", "))
        );
        return responseDTO;
    }
}
