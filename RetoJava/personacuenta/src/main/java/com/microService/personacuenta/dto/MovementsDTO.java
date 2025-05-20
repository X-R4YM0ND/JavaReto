package com.microService.personacuenta.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MovementsDTO {
    private long movementsId;
    private LocalDate movementsDate;
    private double movementsValue;
    private double movementsBalance;
    private List<MovementsDTO> movimientos;
}
