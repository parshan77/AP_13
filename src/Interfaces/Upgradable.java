package Interfaces;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;

public interface Upgradable {
    void upgrade() throws NotEnoughMoneyException, MaxLevelExceeded;
}
