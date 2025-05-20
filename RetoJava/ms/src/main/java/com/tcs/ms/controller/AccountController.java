package com.tcs.ms.controller;

import com.tcs.ms.entities.Account;
import com.tcs.ms.services.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cuentas")
public class AccountController {

    @Autowired
    private IAccountServices accountServices;


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void addAccount(@RequestBody Account newAccount){
        try{
            accountServices.addAccount(newAccount);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo agregar el registro");
        }

    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Account>listAccount(){
        try {
            return accountServices.listAccount();
        } catch (Exception e) {
            throw new RuntimeException("No se muestra");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateAccount(@PathVariable long id, @RequestBody Account updateAccount){
        try {
            updateAccount.setAccountId(id);
            accountServices.updateAccount(updateAccount);
        } catch (Exception e) {
            throw new RuntimeException("Error Edit");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteAccount(@PathVariable long id){
        try {
            accountServices.deleteAccount(id);
        } catch (Exception e) {
            System.out.println("No se elimino el registro");
        }
    }

}
