package com.tcs.ms.services;

import com.tcs.ms.dto.MovementDTO;
import com.tcs.ms.entities.Movements;

import java.util.List;

public interface IMovementsServices {
    public void addMovements(Movements newMovements);
    public List<Movements> listMovements();
    public void updateMovements(Movements updMovements);
    public void deleteMovements(long id);
    Movements registerMovement(MovementDTO movementDTO);
    public List<Movements> getMovementsByACCountNumber(String accountnumber);
}
