package com.example.demobank.services;

import com.example.demobank.models.Account;
import com.example.demobank.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;
    
    public Account addAccount(String holder) {
    
        Account account = new Account(holder);
        System.out.println(account);
        Account saved = accountRepository.save(account);
        System.out.println(saved);
        return saved;
    }
    
    public Account deposit(int amount, Long holderId) {
        Account account = accountRepository.findById(holderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find user"));
        account.addBalance(amount);
        return accountRepository.save(account);
        
    }
    
    public Account withdraw(int amount, Long holderId) {
        Account account = accountRepository.findById(holderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find user"));
        account.removeBalance(amount);
        return accountRepository.save(account);
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account login(Long holderId, String holder) {
        Account account = accountRepository.findById(holderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find user"));
        if(account.getHolder().equalsIgnoreCase(holder)) {
            return account;
        } else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bad credentials");
    }
}
