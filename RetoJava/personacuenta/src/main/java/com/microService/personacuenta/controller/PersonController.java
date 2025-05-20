package com.microService.personacuenta.controller;

import com.microService.personacuenta.entities.Person;
import com.microService.personacuenta.services.IPersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")
public class PersonController {

    @Autowired
    private IPersonServices personservices;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void addPerson(@RequestBody Person newPerson){
        try{
            personservices.addPerson(newPerson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Person>personList(){
        try {
            return personservices.listPerson();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updPerson(@PathVariable long id, @RequestBody Person updateperson){
        try {
            updateperson.setPersonId(id);
            personservices.updatePerson(updateperson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAccount(@PathVariable long id){
        try {
            personservices.deletePerson(id);
        } catch (Exception e) {
            System.out.println("No se elimino el directorio");;
        }
    }




}
