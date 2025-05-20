package com.tcs.ms.services.impl;

import com.tcs.ms.dto.MovementDTO;
import com.tcs.ms.entities.Account;
import com.tcs.ms.entities.Movements;
import com.tcs.ms.repository.IAccountRepository;
import com.tcs.ms.repository.IMovementsRepository;
import com.tcs.ms.services.IMovementsServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Component
public class MovementsServicesImpl implements IMovementsServices {
    @Autowired
    private IMovementsRepository movementsrepo;
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public void addMovements(Movements newMovements){
        try{
            movementsrepo.save(newMovements);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Movements> listMovements(){
        try {
            return movementsrepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateMovements(Movements updateToMov){
        try{
            Optional<Movements> existingMovements = movementsrepo.findById(
                    updateToMov.getMovementsId());
        if (existingMovements.isPresent());
            movementsrepo.save(updateToMov);
        } catch (Exception e) {
            throw new RuntimeException("No existe un registro con este ID");
        }
    }

    @Override
    public void deleteMovements(long id){
    try{
        movementsrepo.deleteById(id);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    }

    @Override
    @Transactional
    public Movements registerMovement(MovementDTO movementDTO) {
        // Buscar la cuenta
        Account account = accountRepository.findByAccountNumber(movementDTO.getAccountNumber());
        if (account == null) {
            throw new NoSuchElementException("Cuenta no encontrada con número: " + movementDTO.getAccountNumber());
        }

        if (movementDTO.getValue() < 0 && account.getAccountInitBalance() + movementDTO.getValue() < 0) {
            throw new RuntimeException("Saldo no disponible");
        }

        // Crear el movimiento
        Movements movement = new Movements();
        movement.setMovementsValue(movementDTO.getValue());
        movement.setMovementsType(movementDTO.getType());
        movement.setMovementsDate(LocalDateTime.now());
        movement.setAccount(account);

        // Actualizar el saldo de la cuenta
        account.setAccountInitBalance(account.getAccountInitBalance() + movementDTO.getValue());
        accountRepository.save(account);

        // Guardar y retornar el movimiento
        return movementsrepo.save(movement);
    }

    @Override
    public List<Movements> getMovementsByACCountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NoSuchElementException("Cuenta no encontrada con número: " + accountNumber);
        }
        return movementsrepo.findByAccount_AccountId(account.getAccountId());
    }

}
