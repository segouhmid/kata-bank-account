package fr.sg.kata.service;

import fr.sg.kata.dto.AmountDTO;
import fr.sg.kata.dto.OperationDTO;
import fr.sg.kata.model.Account;
import fr.sg.kata.model.OperationType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    Account getByAccountNumber(String accountNumber);

    OperationDTO depositOrWithdrawal(String accountNumber, OperationType type, AmountDTO amountDTO);

    List<OperationDTO> getOperationsHistory(String accountNumber);

}
