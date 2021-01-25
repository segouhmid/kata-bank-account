package fr.sg.kata.service;

import fr.sg.kata.model.Account;
import fr.sg.kata.repository.AccountRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void getByAccountNumber_should_return_account(){
        Mockito.when(accountRepository.getByAccountNumber("1")).thenReturn(Account.builder()
                .accountNumber("1")
                .balance(120L)
                .build());

        Account account = accountService.getByAccountNumber("1");

        Assert.assertEquals(account.getAccountNumber(), "1");
    }

}
