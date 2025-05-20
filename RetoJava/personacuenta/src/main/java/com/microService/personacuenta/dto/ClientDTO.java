package com.microService.personacuenta.dto;

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

    // Referencia al objeto PersonDto
    private PersonDTO person;

    // Constructor simplificado
    public ClientDTO(long clientId) {
        this.clientId = clientId;
    }

}
