package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserEmbededDTO {
    private String username;
    private String role;
}
