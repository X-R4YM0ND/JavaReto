package com.microService.personacuenta.services;

import com.microService.personacuenta.entities.Person;

import java.util.List;

public interface IPersonServices {

    public void addPerson(Person newPerson);
    public List<Person> listPerson();
    public void updatePerson(Person updPerson);
    public void deletePerson(long id);

}
