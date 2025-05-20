package com.microService.personacuenta.controller;

import com.microService.personacuenta.dto.AccountMovementsDTO;
import com.microService.personacuenta.dto.ClientDetailDTO;
import com.microService.personacuenta.entities.Client;
import com.microService.personacuenta.entities.Person;
import com.microService.personacuenta.services.IClientServices;
import com.microService.personacuenta.services.IPersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClientController {

    @Autowired
    private IClientServices clientservice;
    private IPersonServices personservice;
    private List<AccountMovementsDTO> cuentas;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkClientExists(@PathVariable long id) {
        try{
            Client client = clientservice.obtainbyId(id);
        return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<ClientDetailDTO> getClientDetail(@PathVariable long id) {
        Client client = clientservice.obtainbyId(id);


        ClientDetailDTO dto = new ClientDetailDTO();
        dto.setClientId(client.getClientId());
        dto.setClientPassword(client.getClientPassword());
        dto.setClientState(client.isClientState());

        dto.setPersonName(client.getPerson().getPersonName());
        dto.setPersonAge(Integer.parseInt(String.valueOf(client.getPerson().getPersonAge())));
        dto.setPersonCI(client.getPerson().getPersonCI());
        dto.setPersonPhone(client.getPerson().getPersonPhone());

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void addClient(@RequestBody Client newClient) {
        try {
            clientservice.addClient(newClient);
        } catch (Exception e) {
            System.out.println();
        }
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Client>listClient(){
        try{
            return clientservice.listClient();
        } catch (Exception e) {
            return null;
        }

    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateClient(@PathVariable long id, @RequestBody Client updClient){
        try{
            updClient.setClientId(id);
            clientservice.updateClient(updClient);
        } catch (Exception e) {
            throw new RuntimeException("No existe este ID");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteClient(@PathVariable long id){
        try{
            clientservice.deleteClient(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

