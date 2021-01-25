package fr.sg.kata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sg.kata.dto.AmountDTO;
import fr.sg.kata.dto.OperationDTO;
import fr.sg.kata.model.Account;
import fr.sg.kata.model.OperationType;
import fr.sg.kata.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountService accountService;

    @Test
    public void getAccountByAccountNumber_should_get_account_by_account_number() throws Exception{
        Mockito.when(accountService.getByAccountNumber("ACC1"))
                .thenReturn(Account.builder().accountNumber("ACC1").balance(2500L).build());

        mvc.perform(MockMvcRequestBuilders
                .get("/accounts/{accountNumber}", "ACC1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("ACC1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value("2500"));
    }

    @Test
    public void depositOrWithdrawal_should_deposit() throws Exception{
        Mockito.when(accountService.depositOrWithdrawal("ACC1", OperationType.DEPOSIT, new AmountDTO(1200L)))
                .thenReturn(OperationDTO.builder().amount(1200L).operationType("DEPOSIT")
                        .operationDate("22-01-2021 10:54:48").balance(2000L).build());

        mvc.perform(MockMvcRequestBuilders
                .put("/accounts/{accountNumber}/{operationType}", "ACC1", OperationType.DEPOSIT)
                .content(asJsonString(new AmountDTO(1200L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value("1200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationType").value("DEPOSIT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationDate").value("22-01-2021 10:54:48"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value("2000"));
    }

    @Test
    public void depositOrWithdrawal_should_withdrawal() throws Exception{
        Mockito.when(accountService.depositOrWithdrawal("ACC2", OperationType.WITHDRAWAL, new AmountDTO(200L)))
                .thenReturn(OperationDTO.builder().amount(200L).operationType("WITHDRAWAL")
                        .operationDate("22-01-2021 16:54:48").balance(1800L).build());

        mvc.perform(MockMvcRequestBuilders
                .put("/accounts/{accountNumber}/{operationType}", "ACC2", OperationType.WITHDRAWAL)
                .content(asJsonString(new AmountDTO(200L)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value("200"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationType").value("WITHDRAWAL"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operationDate").value("22-01-2021 16:54:48"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.balance").value("1800"));
    }

    @Test
    public void getOperationsHistoryByAccountNumber_should_get_history() throws Exception{
        List<OperationDTO> operations = new ArrayList<>();
        operations.add(OperationDTO.builder()
                .operationType("WITHDRAWAL").amount(100L)
                .operationDate("22-01-2021 10:54:48").balance(1900L)
                .build());
        operations.add(OperationDTO.builder()
                .operationType("DEPOSIT").amount(1500L)
                .operationDate("20-01-2021 10:54:13").balance(2000L)
                .build());
        Mockito.when(accountService.getOperationsHistory("ACC1"))
                .thenReturn(operations);

        mvc.perform(MockMvcRequestBuilders
                .get("/accounts/{accountNumber}/history", "ACC1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].operationType").value("DEPOSIT"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].operationDate").value("20-01-2021 10:54:13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].balance").value(2000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].amount").value(1500L));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}