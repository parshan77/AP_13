package Model;

import Exceptions.PlantNotEnoughHealthException;
import Interfaces.VisibleInMap;

public class Plant implements VisibleInMap {
    private int health = 100;
    Position position;;

    //constants
    public static int PLANTING_NEEDED_WATER = 10;

    public Plant(Position position) {
        this.position = position;
    }

    public void decreaseHealth(int amount) throws PlantNotEnoughHealthException{
        if (health < amount)
            throw new PlantNotEnoughHealthException();
        health -= amount;
    }

    @Override
    public void show() {
        //todo
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
