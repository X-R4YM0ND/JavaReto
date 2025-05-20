package com.tcs.ms.repository;

import com.tcs.ms.entities.Movements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovementsRepository extends JpaRepository<Movements, Long> {
    List<Movements> findByAccount_AccountId(long accountId);
}
