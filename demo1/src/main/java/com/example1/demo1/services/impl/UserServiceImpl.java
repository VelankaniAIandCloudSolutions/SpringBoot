package com.example1.demo1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example1.demo1.entities.User;
import com.example1.demo1.entities.BankAccount;
import com.example1.demo1.repositories.UserRepository;
import com.example1.demo1.repositories.BankAccountRepository;
import com.example1.demo1.dto.UserDTO;
import com.example1.demo1.services.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertToDTO);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setBankAccounts(userDetails.getBankAccountIds().stream()
                .map(bankAccountId -> bankAccountRepository.findById(bankAccountId)
                        .orElseThrow(() -> new RuntimeException("BankAccount not found")))
                .collect(Collectors.toList()));
        User updatedUser = userRepository.save(user);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setBankAccountIds(user.getBankAccounts().stream().map(BankAccount::getId).collect(Collectors.toList()));
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setBankAccounts(userDTO.getBankAccountIds().stream()
                .map(bankAccountId -> bankAccountRepository.findById(bankAccountId)
                        .orElseThrow(() -> new RuntimeException("BankAccount not found")))
                .collect(Collectors.toList()));
        return user;
    }
}
