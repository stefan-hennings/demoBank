package com.example.demobank.controllers;


import com.example.demobank.models.Account;
import com.example.demobank.services.AccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/account")
public class AccountController {
    final AccountService accountService;
    
    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
    
    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestParam String holder) throws JsonProcessingException {
        return ResponseEntity.ok(accountService.addAccount(holder));
    }
    
    @PostMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestParam int amount, @RequestParam Long holderId){
        log.info("Deposit >>> amount: " + amount + " & holderId: " +holderId);
        return ResponseEntity.ok(accountService.deposit(amount, holderId));
    }
    
    
    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestParam int amount, @RequestParam Long holderId) {
        return ResponseEntity.ok(accountService.withdraw(amount, holderId));
    }

    @GetMapping("login")
    public ResponseEntity<Account> login(@RequestParam Long holderId, @RequestParam String holder) {
        return ResponseEntity.ok(accountService.login(holderId, holder));
    }
}
