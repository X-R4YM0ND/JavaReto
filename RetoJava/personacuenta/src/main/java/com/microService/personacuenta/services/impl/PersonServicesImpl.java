package com.microService.personacuenta.services.impl;

import com.microService.personacuenta.entities.Person;
import com.microService.personacuenta.repository.IPersonRepository;
import com.microService.personacuenta.services.IPersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class PersonServicesImpl implements IPersonServices {

    @Autowired
    private IPersonRepository personrepo;

    @Override
    public void addPerson(Person newPerson){
        try{
            personrepo.save(newPerson);
        }catch (Exception e){
            System.out.println("No se guardo el registro");
        }
    }

    @Override
    public List<Person>listPerson(){
        try{
            return personrepo.findAll();
        }catch (Exception e){
            System.out.println("No existen registros.");
            return null;
        }
    }

    @Override
    public void updatePerson(Person updatePerson){
        try{
            Optional<Person> existPerson = personrepo.findById(
                    updatePerson.getPersonId());
            if(existPerson.isPresent()){
                personrepo.save(updatePerson);
            }
        }catch (Exception e){
            System.out.println("No se actualizo el registro");
        }
    }

    @Override
    public void deletePerson(long id){
        try{
            personrepo.deleteById(id);
        }catch (Exception e){
            System.out.println("No se elimino el registro");
        }
    }

}
