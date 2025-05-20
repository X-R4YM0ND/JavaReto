package com.microService.personacuenta.dto;

import com.microService.personacuenta.entities.Client;
import com.microService.personacuenta.entities.Person;
import lombok.Data;

import java.util.List;

@Data
public class ClientDetailDTO {
    private Person person;
    private Client client;
    private List<AccountMovementsDTO> accounts;

    private Long clientId;
    private String clientPassword;
    private boolean clientState;


    // Datos de la persona
    private String PersonName;
    private int personAge;
    private String personCI;
    private String personPhone;
}
