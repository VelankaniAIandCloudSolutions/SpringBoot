package com.example1.demo1.controllers;

import com.example1.demo1.services.BankService;
import com.example1.demo1.dto.BankDTO;
import com.example1.demo1.dto.BankAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    @Autowired
    private BankService bankService;

    // Get all banks
    @GetMapping
    public ResponseEntity<List<BankDTO>> getAllBanks() {
        List<BankDTO> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }

    // Get bank by ID
    @GetMapping("/{id}")
    public ResponseEntity<BankDTO> getBankById(@PathVariable Long id) {
        Optional<BankDTO> bank = bankService.getBankById(id);
        return bank.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new bank
    @PostMapping
    public ResponseEntity<BankDTO> createBank(@RequestBody BankDTO bankDTO) {
        BankDTO createdBank = bankService.createBank(bankDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBank);
    }

    // Update bank details
    @PutMapping("/{id}")
    public ResponseEntity<BankDTO> updateBank(@PathVariable Long id, @RequestBody BankDTO bankDetails) {
        BankDTO updatedBank = bankService.updateBank(id, bankDetails);
        return ResponseEntity.ok(updatedBank);
    }

    // Delete a bank
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }

    // Create a new bank account for a specific bank
    @PostMapping("/{bankId}/accounts")
    public ResponseEntity<BankAccountDTO> createBankAccount(@RequestBody BankAccountDTO bankAccountDTO,
            @PathVariable Long bankId) {
        BankAccountDTO createdBankAccount = bankService.createBankAccount(bankAccountDTO, bankId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBankAccount);
    }
}
