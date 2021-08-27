package com.example.demobank.services;

import com.example.demobank.models.Account;
import com.example.demobank.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService {
    
    private final AccountRepository accountRepository;

    RestTemplate restTemplate = new RestTemplate();
    
    public Account addAccount(String holder) {

        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/risk/"+ holder, String.class);

        Account account = new Account(holder);
        System.out.println(account);
        Account saved = accountRepository.save(account);
        System.out.println(saved);
        return saved;
    }
    
    public Account deposit(int amount, Long holderId) {
        Account account = accountRepository.findById(holderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fel kontonummer"));
            account.addBalance(amount);


        return accountRepository.save(account);
        
    }
    
    public Account withdraw(int amount, Long holderId) {
        Account account = accountRepository.findById(holderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fel kontonummer"));
        account.removeBalance(amount);
        return accountRepository.save(account);
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account login(Long holderId, String holder) {
        Account account = accountRepository.findById(holderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fel kontonummer"));
        if(account.getHolder().equalsIgnoreCase(holder)) {
            return account;
        } else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Fel namn");
    }
}
