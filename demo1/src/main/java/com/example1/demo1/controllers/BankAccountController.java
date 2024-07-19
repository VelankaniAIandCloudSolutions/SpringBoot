package com.example1.demo1.controllers;

import com.example1.demo1.dto.BankAccountDTO;
import com.example1.demo1.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<List<BankAccountDTO>> getAllBankAccounts() {
        List<BankAccountDTO> bankAccounts = bankAccountService.getAllBankAccounts();
        return ResponseEntity.ok(bankAccounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDTO> getBankAccountById(@PathVariable Long id) {
        Optional<BankAccountDTO> bankAccount = bankAccountService.getBankAccountById(id);
        return bankAccount.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankAccountDTO> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        BankAccountDTO createdBankAccount = bankAccountService.createBankAccount(bankAccountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBankAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDTO> updateBankAccount(@PathVariable Long id,
            @RequestBody BankAccountDTO bankAccountDTO) {
        BankAccountDTO updatedBankAccount = bankAccountService.updateBankAccount(id, bankAccountDTO);
        return ResponseEntity.ok(updatedBankAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        bankAccountService.deleteBankAccount(id);
        return ResponseEntity.noContent().build();
    }

}
