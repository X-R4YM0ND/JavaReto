package com.microService.personacuenta.repository;

import com.microService.personacuenta.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface IClientRepository extends JpaRepository<Client, Long> {
}
