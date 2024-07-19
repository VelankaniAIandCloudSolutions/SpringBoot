package com.example1.demo1.repositories;

import com.example1.demo1.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}