package com.example.demobank.controllers;


import com.example.demobank.models.Account;
import com.example.demobank.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/account")
public class AccountController {
    final AccountService accountService;
    
    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestParam String holder) {
        return ResponseEntity.ok(accountService.addAccount(holder));
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestParam int amount, @RequestParam Long holderId){
        return ResponseEntity.ok(accountService.deposit(amount, holderId));
    }
    
    
    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestParam int amount, @RequestParam Long holderId) {
        return ResponseEntity.ok(accountService.withdraw(amount, holderId));
    }
}
