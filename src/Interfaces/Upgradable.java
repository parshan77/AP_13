package Interfaces;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Exceptions.WorkshopMaxLevelExceeded;

public interface Upgradable {
    void upgrade() throws NotEnoughMoneyException, MaxLevelExceeded;
}
