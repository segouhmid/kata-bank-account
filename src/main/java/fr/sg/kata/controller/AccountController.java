package fr.sg.kata.controller;

import fr.sg.kata.dto.OperationDTO;
import fr.sg.kata.dto.AmountDTO;
import fr.sg.kata.model.Account;
import fr.sg.kata.model.OperationType;
import fr.sg.kata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping(value="/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) { return accountService.getByAccountNumber(accountNumber); }

    @PutMapping("/{accountNumber}/{operationType}")
    public OperationDTO depositOrWithdrawal(@PathVariable String accountNumber, @PathVariable OperationType operationType, @RequestBody AmountDTO amountDTO) {
        return accountService.depositOrWithdrawal(accountNumber, operationType, amountDTO);
    }

    @GetMapping(value="/{accountNumber}/history")
    public List<OperationDTO> getOperationsHistory(@PathVariable("accountNumber") String accountNumber) {
        return accountService.getOperationsHistory(accountNumber);
    }
}
