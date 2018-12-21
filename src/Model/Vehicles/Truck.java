package Model.Vehicles;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Tradable;
import Model.Direction;
import Model.Mission;
import Model.Position;

import java.util.ArrayList;

public class Truck extends Vehicle {
    private static int TRUCK_CAPACITY;

    //todo: Singleton
    private Truck(Mission mission) {
        super(mission, TRUCK_CAPACITY);
    }


    public void trade(ArrayList<Tradable> tradingObjects) {
        int income = 0 ;//= 0
        for (Tradable tradingObject : tradingObjects) {
            income += tradingObject.getSellCost();
        }
        mission.addMoney(income);
        super.tradingObjects.clear();
    }
///////////////////////////////////////////////////////////////////////////////////
    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceeded {

    }

    @Override
    public void show() {

    }

    @Override
    public Position getPosition() {
        return null;
    }
}
