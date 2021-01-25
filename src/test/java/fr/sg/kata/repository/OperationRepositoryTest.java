package fr.sg.kata.repository;

import fr.sg.kata.model.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

@SpringBootTest
@SqlGroup({
        @Sql(scripts = "classpath:scripts/feed_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:scripts/purge_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
class OperationRepositoryTest {

    @Autowired
    private OperationRepository operationRepository;

    @Test
    public void getAllOperationByAccountNumber_should_return_operations_history(){
        List<Operation> operations = operationRepository.getAllOperationByAccountNumber("ACC1");

        Assertions.assertEquals(2, operations.size());
    }


}