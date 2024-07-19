package com.example1.demo1.services;

import com.example1.demo1.dto.BankDTO;
import com.example1.demo1.dto.BankAccountDTO;

import java.util.List;
import java.util.Optional;

public interface BankService {
    List<BankDTO> getAllBanks();

    Optional<BankDTO> getBankById(Long id);

    BankDTO createBank(BankDTO bankDTO);

    BankAccountDTO createBankAccount(BankAccountDTO bankAccountDTO, Long bankId);

    BankDTO updateBank(Long id, BankDTO bankDetails);

    void deleteBank(Long id);
}
