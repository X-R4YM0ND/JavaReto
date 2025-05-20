package com.tcs.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountSummaryDTO {

        private long accountId;
        private String accountNumber;
        private String accountType;

}
