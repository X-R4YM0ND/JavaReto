package com.tcs.ms.repository;

import com.tcs.ms.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
}
