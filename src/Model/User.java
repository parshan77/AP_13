package Model;

import Exceptions.NotEnoughMoneyException;

public class User {
    private long money;

    public void addMoney(long amount) {
        money += amount;
    }

    public void spendMoney(long amount) throws NotEnoughMoneyException {
        if (amount > money)
            throw new NotEnoughMoneyException();
        money -= amount;
    }
}
