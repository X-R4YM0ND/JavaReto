package com.microService.personacuenta.services.impl;

import com.microService.personacuenta.entities.Client;
import com.microService.personacuenta.repository.IClientRepository;
import com.microService.personacuenta.repository.IPersonRepository;
import com.microService.personacuenta.services.IClientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientServicesImpl implements IClientServices {
    @Autowired
    private IClientRepository clientrepo;
    private IPersonRepository personrepo;

    @Override
    public void addClient(Client newClient){

        try{
            clientrepo.save(newClient);
        }catch (Exception e){
            System.out.println();
        }

    }

    @Override
    public List<Client> listClient(){
        try{
            return clientrepo.findAll();
        }catch (Exception e){
            System.out.println();
            return null;
        }
    }

    @Override
    public Client obtainbyId(long id){
        return clientrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public void updateClient(Client updateClient){

        try{
             Optional<Client> existClient = clientrepo.findById(
                     updateClient.getClientId());
             if(existClient.isPresent()){
                 clientrepo.save(updateClient);
             }else{
                 System.out.println("No existe");
             }
        }catch (Exception e){
            System.out.println();
        }

    }

    @Override
    public  void deleteClient(long id) {

        try {
            clientrepo.deleteById(id);
        } catch (Exception e) {
            System.out.println();
        }

    }

}
