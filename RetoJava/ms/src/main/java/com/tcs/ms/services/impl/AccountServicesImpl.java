package com.tcs.ms.services.impl;

import com.tcs.ms.entities.Account;
import com.tcs.ms.repository.IAccountRepository;
import com.tcs.ms.services.IAccountServices;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class AccountServicesImpl implements IAccountServices {
    @Autowired
    private IAccountRepository accountrepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservice.personacuenta.url}")
    private String personacuentaUrl;

//    @Transactional
//    public Account createAccount(Account account) {
//        boolean clientExists = checkClientExists(account.getClientId());
//        if (!clientExists) {
//            throw new RuntimeException("El cliente con ID " + account.getClientId() + " no existe");
//        }
//
//        return accountrepo.save(account);
//    }
//
//    private boolean checkClientExists(long clientId) {
//        try {
//            // Llamar al otro microservicio para verificar si el cliente existe
//            ResponseEntity<Boolean> response = restTemplate.getForEntity(
//                    personacuentaUrl + "/api/clients/exists/" + clientId,
//                    Boolean.class
//            );
//            return response.getBody() != null && response.getBody();
//        } catch (Exception e) {
//            // Manejar errores de comunicación
//            throw new RuntimeException("Error al verificar el cliente: " + e.getMessage());
//        }
//    }

    @Override
    public void addAccount(Account newAccount) {
        try {

            accountrepo.save(newAccount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> listAccount() {
        try {
            return accountrepo.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAccount(Account updateAccount){
        try{
            Optional<Account> existAccount = accountrepo.findById(
                    updateAccount.getAccountId());
            if (existAccount.isPresent()){
                accountrepo.save(updateAccount);
            }else {
                System.out.println("No existe un registro con este ID");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteAccount(long id){
        try {
            accountrepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
