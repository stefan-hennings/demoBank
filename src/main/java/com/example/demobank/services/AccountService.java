package com.example.demobank.services;

import com.example.demobank.models.Account;
import com.example.demobank.models.ApprovalDto;
import com.example.demobank.repositories.AccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountService {
    private String baseUrl= "http://localhost:";
    private String riskPort= "8082/";
    private String endpoint = "risk/";

    @Value("${api_url}")
    private String url;
    
    private final AccountRepository accountRepository;

    RestTemplate restTemplate = new RestTemplate();
    
    public Account addAccount(String holder) throws JsonProcessingException {

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url + holder, String.class);

        System.out.println(forEntity.getBody());

        ObjectMapper mapper = new ObjectMapper();
        ApprovalDto approvalDto = mapper.readValue(forEntity.getBody(), ApprovalDto.class);

        System.out.println(approvalDto.isApproved());

        if(!approvalDto.isApproved()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Kreditprövning misslyckad");
        }

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
