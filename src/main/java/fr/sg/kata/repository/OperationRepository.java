package fr.sg.kata.repository;

import fr.sg.kata.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {
    @Query(value = "select op from Operation op where op.account.accountNumber= :accountNumber order by op.operationDate desc")
    List<Operation> getAllOperationByAccountNumber(@Param("accountNumber") String accountNumber);
}
