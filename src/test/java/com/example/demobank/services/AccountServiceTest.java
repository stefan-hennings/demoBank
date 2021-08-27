package com.example.demobank.services;

import com.example.demobank.models.Account;
import com.example.demobank.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by Patrik Melander
 * Date: 2021-08-27
 * Time: 10:33
 * Project: demoBank
 * Copyright: MIT
 */
class AccountServiceTest {
    Account account = new Account("Patrik");
/*
    @Test
    void addAccountTest() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        when(accountRepository.save(account)).thenReturn(account);


        assertEquals(account,accountService.addAccount("Patrik"));

        verify(accountRepository, times(1)).save(any());

    }

    @Test
    void depositTest() {
        long id = 1;
        AccountRepository accountRepository = mock(AccountRepository.class);
        AccountService accountService = new AccountService(accountRepository);
        //when(accountRepository.findById(id)).thenReturn(account);

    }

    @Test
    void withdrawTest() {
        accountRepository.findById
    }

    @Test
    void getAllAccountsTest() {
        accountRepository.findAll();
    }

    @Test
    void loginTest() {
    }
    */
}