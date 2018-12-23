package Model.Vehicles;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.Tradable;
import Model.Direction;
import Model.Mission;
import Model.Position;

import java.util.ArrayList;

public class Truck extends Vehicle {

    //connstants
    private static int TRUCK_CAPACITY = 20;
    private static int[] VEHICLE_UPGRADE_COSTS = {100, 200, 300};
    private static int[] BOX_COUNTS = {2, 3, 4, 5};
    private static int[] TRAVEL_DURATION = {20, 15, 10, 5};//moddat zamane safar

    private int boxCount = 2;
    private int travelDuration = 20;

    //todo: Singleton
    private Truck(Mission mission) throws NotValidCoordinatesException {
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

    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        super.upgrade();
        boxCount = BOX_COUNTS[this.level];
    }

    @Override
    public void show() {

    }

    @Override
    public Position getPosition() {
        return this.position;
    }
}
