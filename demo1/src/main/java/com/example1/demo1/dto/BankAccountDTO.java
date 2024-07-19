package com.example1.demo1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BankAccountDTO {
    // getters and setters
    private Long id;
    private String accountNumber;
    private Long bankId;
    private Long userId;

}
