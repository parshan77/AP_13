package Model;

import Exceptions.PlantNotEnoughHealthException;
import Interfaces.Visible;

public class Plant implements Visible {
    private double health = 100;

    //constants
    public static int PLANTING_NEEDED_WATER = 10;

    public Plant() {
    }

    public void decreaseHealth(double amount) throws PlantNotEnoughHealthException{
        if (health < amount)
            throw new PlantNotEnoughHealthException();
        health -= amount;
    }

    @Override
    public void show() {
        //todo
    }
}
