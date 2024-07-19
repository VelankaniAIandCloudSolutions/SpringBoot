package com.example1.demo1.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserDTO {
    // Getters and setters
    private Long id;
    private String name;
    private String email;
    private List<Long> bankAccountIds;

}
