package com.tcs.ms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long clientId;
    private String clientPassword;
    private Boolean clientState;

    private PersonDTO person;

    public ClientDTO(long clientId) {
        this.clientId = clientId;
    }

}
