package fr.sg.kata.repository;

import fr.sg.kata.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@SqlGroup({
        @Sql(scripts = "classpath:scripts/feed_data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "classpath:scripts/purge_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)})
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void getByAccountNumber_should_return_account(){
        Account account = accountRepository.getByAccountNumber("ACC2");

        Assertions.assertEquals(2, account.getId());
    }


}
