package com.example.demobank.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String holder;
    
    private int balance;
    
    public Account(String holder) {
        this.holder = holder;
        this.balance = 0;
    }
    
    public Account addBalance(int amount) {
        if(isPositiveAmount(amount)) {
          balance += amount;
        }
        return this;
    }
    
    public Account removeBalance(int amount) {
        if (isPositiveAmount(amount) && balance>=amount){
           balance -= amount;
           return this;
        }
        
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds");
    }
    
    private boolean isPositiveAmount(int amount) {
        if(amount>0) {
            return true;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot use negative numbers");
    }
}
