package Model.Identity;

import Exceptions.NotFoundException;
import Exceptions.WrongPasswordException;

import java.util.ArrayList;

public class Game {
    private static final Game game = new Game();

    private Game() {

    }

    public static Game getInstance() {
        return game;
    }

    private ArrayList<Account> accounts = new ArrayList<>();

    public ArrayList<Account> getAllAccounts() {
        return accounts;
    }

    public Account signUp(String username) {
        Account account = new Account(username);
        accounts.add(account);
        return account;
    }

    public Account login(String username) throws NotFoundException {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        throw new NotFoundException();
    }
}
