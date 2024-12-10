package com.example.demo.controller;

import com.example.demo.DTO.UserCreateDTO;
import com.example.demo.DTO.UserResponseDTO;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        return userService.createUser(userCreateDTO);
    }

    @GetMapping("/{username}")
    public UserResponseDTO getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
