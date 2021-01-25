package fr.sg.kata.service.impl;

import fr.sg.kata.dto.OperationDTO;
import fr.sg.kata.exception.AccountNotFoundException;
import fr.sg.kata.exception.BalanceInsufficientException;
import fr.sg.kata.mapper.OperationMapper;
import fr.sg.kata.model.Account;
import fr.sg.kata.repository.OperationRepository;
import fr.sg.kata.dto.AmountDTO;
import fr.sg.kata.model.Operation;
import fr.sg.kata.model.OperationType;
import fr.sg.kata.repository.AccountRepository;
import fr.sg.kata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private OperationMapper operationMapper;

    public Account getByAccountNumber(String accountNumber){
        return accountRepository.getByAccountNumber(accountNumber);
    }

    public OperationDTO depositOrWithdrawal(String accountNumber, OperationType type, AmountDTO amountDTO) {

        Account account = Optional.of(accountRepository.getByAccountNumber(accountNumber))
                .orElseThrow(() -> new AccountNotFoundException("Account not found, please check your account number"));

        Operation operation = Operation.builder().amount(amountDTO.getValue()).account(account).
                operationDate(LocalDateTime.now()).operationType(type).build();
        List<Operation> operations = account.getOperations();

        switch (type) {
            case DEPOSIT:
                account.setBalance(account.getBalance() + amountDTO.getValue());
                break;
            case WITHDRAWAL:
                if(amountDTO.getValue() > account.getBalance()) {
                    throw new BalanceInsufficientException("balance is insufficient for a withdrawal.");
                }
                account.setBalance(account.getBalance() - amountDTO.getValue());
                break;
            default:
                throw new IllegalArgumentException("given operation does not match any type.");
        }
        operation = operationRepository.save(operation);
        accountRepository.save(account);
        return operationMapper.operationToOperationDTO(operation);
    }

    public List<OperationDTO> getOperationsHistory(String accountNumber){
        List<Operation> operations = operationRepository.getAllOperationByAccountNumber(accountNumber);
        List<OperationDTO> operationsDTOs = new ArrayList<>();
        /*
         *  update Balance to each operation For Print Statement
         */
        for(int i= 0; i< operations.size(); i++){
            Operation operation = operations.get(i);
            operationsDTOs.add(operationMapper.operationToOperationDTO(operation));
            if(operation.getOperationType() == OperationType.DEPOSIT){
                operation.getAccount().setBalance(operation.getAccount().getBalance() - operation.getAmount());
            }
            if(operation.getOperationType() == OperationType.WITHDRAWAL){
                operation.getAccount().setBalance(operation.getAccount().getBalance() + operation.getAmount());
            }
        }
        return operationsDTOs;
    }
}

