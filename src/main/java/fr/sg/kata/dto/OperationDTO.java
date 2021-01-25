package fr.sg.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class OperationDTO {
    private String operationType;
    private String operationDate;
    private Long amount;
    private Long balance;
}
