package Model;

import Exceptions.NotFoundException;

import java.util.ArrayList;

public class Game {
    private ArrayList<Account> accounts;

    public void addAccount(String userName) {
        accounts.add(new Account(userName));
    }

    public Account getAccount(String userName) throws NotFoundException {
        for (Account account : accounts)
            if (account.getUserName().toLowerCase().equals(userName.toLowerCase())) return account;
        throw new NotFoundException();
    }
}
