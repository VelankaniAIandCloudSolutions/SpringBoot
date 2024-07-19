package com.example1.demo1.services;

import com.example1.demo1.dto.BankAccountDTO;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    List<BankAccountDTO> getAllBankAccounts();

    Optional<BankAccountDTO> getBankAccountById(Long id);

    BankAccountDTO createBankAccount(BankAccountDTO bankAccountDTO);

    BankAccountDTO updateBankAccount(Long id, BankAccountDTO bankAccountDetails);

    void deleteBankAccount(Long id);
}
