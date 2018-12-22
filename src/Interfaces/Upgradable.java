package Interfaces;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;

public interface Upgradable {
    void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException;
}
