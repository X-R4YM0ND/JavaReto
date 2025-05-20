package com.tcs.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementDTO {
    private Double value;
    private String type;
    private String accountNumber;
}
