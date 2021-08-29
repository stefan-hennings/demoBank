package com.example.demobank.models;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Patrik Melander
 * Date: 2021-08-27
 * Time: 09:27
 * Project: demoBank
 * Copyright: MIT
 */
class AccountTest {
    Account account= new Account();

    @Test
    void createAccount(){
        Account accountTest= new Account();
        assertEquals(0,account.getBalance());

    }


    @Test
    void addBalanceTest() {
        account.addBalance(100);
        assertEquals(100,account.getBalance());

    }

    @Test
    void removeBalanceTest() {
        account.addBalance(100);
        account.removeBalance(50);

        assertEquals(50,account.getBalance());

    }

    @Test
    void removeBalanceTestExeption() {
        account.addBalance(100);

        assertThrows(ResponseStatusException.class, () -> account.removeBalance(200));

    }

    @Test
    void isPositiveAmountTrueTest(){
        assertTrue(account.isPositiveAmount(100));
    }

    @Test
    void isPositiveAmountFalseTest(){
        assertThrows(ResponseStatusException.class, () -> account.isPositiveAmount(-100));
    }
}
