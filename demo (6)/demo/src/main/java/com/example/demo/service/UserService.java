package com.example.demo.service;

import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.DTO.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserCreateDTO userCreateDTO);
    UserResponseDTO getUserByUsername(String username);
}
