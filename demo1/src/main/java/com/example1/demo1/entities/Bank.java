package com.example1.demo1.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BankAccount> bankAccounts;

}
