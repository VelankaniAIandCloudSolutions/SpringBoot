package com.example1.demo1.services;

import com.example1.demo1.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDetails);

    void deleteUser(Long id);
}
