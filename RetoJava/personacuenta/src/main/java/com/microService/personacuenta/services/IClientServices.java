package com.microService.personacuenta.services;

import com.microService.personacuenta.entities.Client;

import java.util.List;

public interface IClientServices {

    public void addClient(Client newClient);
    public List<Client> listClient();
    public Client obtainbyId(long id);
    public void updateClient(Client updClient);
    public void deleteClient(long id);

}
