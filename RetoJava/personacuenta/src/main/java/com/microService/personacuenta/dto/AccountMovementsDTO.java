package com.microService.personacuenta.dto;

import lombok.Data;

import java.util.List;
@Data
public class AccountMovementsDTO {
    private Long id;
    private String AccountType;
    private Double AccountInitBalance;
    private String AccountState;
    private List<MovementsDTO> movements;
}
