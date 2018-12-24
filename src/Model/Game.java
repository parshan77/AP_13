package Model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Account> accounts;

    public void addAccount(String userName) {
        accounts.add(new Account(userName));
    }

    public Account getAccount(String userName) {
        for (Account account : accounts)
            if (account.getUserName().equals(userName)) return account;
        return null;
    }
}
