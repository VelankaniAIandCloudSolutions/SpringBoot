package com.example1.demo1.repositories;

import com.example1.demo1.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}