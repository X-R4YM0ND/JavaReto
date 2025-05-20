package com.tcs.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    private String accountNumber;
    private String accountType;
    private double accountInitBalance;
    private boolean accountState;

    // Para manejar el clientId anidado
    private ClientRequestDTO client;

}
