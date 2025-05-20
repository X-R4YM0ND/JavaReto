package com.tcs.ms.services;

import com.tcs.ms.entities.Account;

import java.util.List;

public interface IAccountServices {
    public void addAccount(Account newAccount);
    public List<Account> listAccount();
    public void updateAccount(Account updAccount);
    public void deleteAccount(long id);
}
