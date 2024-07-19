package com.example1.demo1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example1.demo1.entities.Bank;
import com.example1.demo1.entities.BankAccount;
import com.example1.demo1.repositories.BankRepository;
import com.example1.demo1.repositories.BankAccountRepository;
import com.example1.demo1.dto.BankDTO;
import com.example1.demo1.dto.BankAccountDTO;
import com.example1.demo1.services.BankService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
     public List<BankDTO> getAllBanks() {
        List<Bank> banks = bankRepository.findAll();
        return banks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<BankDTO> getBankById(Long id) {
        Optional<Bank> bank = bankRepository.findById(id);
        return bank.map(this::convertToDTO);
    }

    @Override
    public BankDTO createBank(BankDTO bankDTO) {
        Bank bank = convertToEntity(bankDTO);
        Bank savedBank = bankRepository.save(bank);
        return convertToDTO(savedBank);
    }

    @Override
    public BankAccountDTO createBankAccount(BankAccountDTO bankAccountDTO, Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        BankAccount bankAccount = convertToEntity(bankAccountDTO);
        bankAccount.setBank(bank);
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        return convertToDTO(savedBankAccount);
    }

    @Override
    public BankDTO updateBank(Long id, BankDTO bankDetails) {
        Bank bank = bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
        bank.setName(bankDetails.getName());
        Bank updatedBank = bankRepository.save(bank);
        return convertToDTO(updatedBank);
    }

    @Override
    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    private BankDTO convertToDTO(Bank bank) {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(bank.getId());
        bankDTO.setName(bank.getName());
        return bankDTO;
    }

    private BankAccountDTO convertToDTO(BankAccount bankAccount) {
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(bankAccount.getId());
        bankAccountDTO.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountDTO.setBankId(bankAccount.getBank().getId());
        bankAccountDTO.setUserId(bankAccount.getUser().getId());
        return bankAccountDTO;
    }

    private Bank convertToEntity(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setId(bankDTO.getId());
        bank.setName(bankDTO.getName());
        return bank;
    }

    private BankAccount convertToEntity(BankAccountDTO bankAccountDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountDTO.getId());
        bankAccount.setAccountNumber(bankAccountDTO.getAccountNumber());
        return bankAccount;
    }
}
