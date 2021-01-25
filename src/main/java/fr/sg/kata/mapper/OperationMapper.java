package fr.sg.kata.mapper;

import fr.sg.kata.dto.OperationDTO;
import fr.sg.kata.model.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    @Mappings({
            @Mapping(target = "operationType", source = "operationType"),
            @Mapping(target = "operationDate", source = "operationDate", dateFormat = "dd-MM-yyyy HH:mm:ss"),
            @Mapping(target = "amount", source = "amount"),
            @Mapping(target = "balance", source = "account.balance")
    })
    OperationDTO operationToOperationDTO(Operation operation);

}
