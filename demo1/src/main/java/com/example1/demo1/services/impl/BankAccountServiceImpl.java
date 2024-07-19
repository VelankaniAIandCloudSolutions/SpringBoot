package com.example1.demo1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example1.demo1.entities.BankAccount;
import com.example1.demo1.entities.Bank;
import com.example1.demo1.entities.User;
import com.example1.demo1.repositories.BankAccountRepository;
import com.example1.demo1.repositories.BankRepository;
import com.example1.demo1.repositories.UserRepository;
import com.example1.demo1.dto.BankAccountDTO;
import com.example1.demo1.services.BankAccountService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<BankAccountDTO> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccountDTO> getBankAccountById(Long id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        return bankAccount.map(this::convertToDTO);
    }

    @Override
    public BankAccountDTO createBankAccount(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = convertToEntity(bankAccountDTO);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return convertToDTO(savedBankAccount);
    }

    @Override
    public BankAccountDTO updateBankAccount(Long id, BankAccountDTO bankAccountDetails) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BankAccount not found"));
        bankAccount.setAccountNumber(bankAccountDetails.getAccountNumber());

        Bank bank = bankRepository.findById(bankAccountDetails.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        bankAccount.setBank(bank);

        User user = userRepository.findById(bankAccountDetails.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        bankAccount.setUser(user);

        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
        return convertToDTO(updatedBankAccount);
    }

    @Override
    public void deleteBankAccount(Long id) {
        bankAccountRepository.deleteById(id);
    }

    private BankAccountDTO convertToDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(bankAccount.getId());
        bankAccountDTO.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountDTO.setBankId(bankAccount.getBank().getId());
        bankAccountDTO.setUserId(bankAccount.getUser().getId());
        return bankAccountDTO;
    }

    private BankAccount convertToEntity(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountDTO.getId());
        bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());

        Bank bank = bankRepository.findById(bankAccountDTO.getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        bankAccount.setBank(bank);

        User user = userRepository.findById(bankAccountDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        bankAccount.setUser(user);

        return bankAccount;
    }
}
