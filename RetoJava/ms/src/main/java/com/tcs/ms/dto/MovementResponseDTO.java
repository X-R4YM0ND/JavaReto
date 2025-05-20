package com.tcs.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class MovementResponseDTO {
    private long movementsId;
    private LocalDateTime movementsDate;
    private String movementsType;
    private double movementsValue;
    private double movementsBalance;
    private String accountNumber;

    private AccountSummaryDTO account;

}
