package fr.sg.kata.repository;

import fr.sg.kata.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select acc from Account acc where acc.accountNumber = :accountNumber")
    Account getByAccountNumber(@Param("accountNumber") String accountNumber);
}
