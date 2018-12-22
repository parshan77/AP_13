package Model.Animals.Seekers;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Model.Animals.Seeker;
import Model.Direction;

public class Cat extends Seeker implements Upgradable {
    private int level = 0;
    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {

    }

    @Override
    public void show() {

    }
}
