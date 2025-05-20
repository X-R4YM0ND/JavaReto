package com.tcs.ms.controller;

import com.tcs.ms.dto.AccountRequestDTO;
import com.tcs.ms.dto.AccountSummaryDTO;
import com.tcs.ms.dto.MovementDTO;
import com.tcs.ms.dto.MovementResponseDTO;
import com.tcs.ms.entities.Account;
import com.tcs.ms.entities.Movements;
import com.tcs.ms.services.IAccountServices;
import com.tcs.ms.services.IMovementsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movimientos")
public class MovementsController {

    @Autowired
    private IMovementsServices movementsServices;

    @Autowired
    private IAccountServices accountServices;


    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getMovementsByAccountNumber(@PathVariable String accountNumber) {
        try {
            List<Movements> movements = movementsServices.getMovementsByACCountNumber(accountNumber);

            List<MovementResponseDTO> responseList = movements.stream()
                    .map(movement -> {
                        MovementResponseDTO dto = new MovementResponseDTO();
                        dto.setMovementsId(movement.getMovementsId());
                        dto.setMovementsDate(movement.getMovementsDate());
                        dto.setMovementsType(movement.getMovementsType());
                        dto.setMovementsValue(movement.getMovementsValue());
                        dto.setMovementsBalance(movement.getMovementsBalance());

                        if (movement.getAccount() != null) {
                            AccountSummaryDTO accountDto = new AccountSummaryDTO(
                                    movement.getAccount().getAccountId(),
                                    movement.getAccount().getAccountNumber(),
                                    movement.getAccount().getAccountType()
                            );
                            dto.setAccount(accountDto);
                        }

                        return dto;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(responseList);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontro");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR");
        }
    }



    @PostMapping
    public ResponseEntity<?> registerMovement(@RequestBody MovementDTO movementDTO) {
        try {
            Movements savedMovement = movementsServices.registerMovement(movementDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovement);
        } catch (RuntimeException e) {
            Map<String, String> response = new HashMap<>();
            if (e.getMessage().equals("Saldo no disponible")) {
                response.put("message", "Saldo no disponible");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else if (e instanceof NoSuchElementException) {
                response.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                response.put("message", "Error al registrar el movimiento: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }
    }


    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateMovements(@PathVariable long id, @RequestBody Movements updateMovements){
        try {
            updateMovements.setMovementsId(id);
            movementsServices.updateMovements(updateMovements);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteMovements(@PathVariable long id){
        try {
            movementsServices.deleteMovements(id);
        } catch (Exception e) {
            System.out.println("No se elimino el directorio");
        }
    }
}
