package Model.Vehicles;

import Exceptions.CapacityExceededException;
import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.Tradable;
import Model.Placement.Direction;
import Model.Mission;
import Model.Placement.Position;

import java.util.ArrayList;

public class Truck extends Vehicle {

    private static int[] CAPACITYS = {40,60,80,100};
    private static int[] VEHICLE_UPGRADE_COSTS = {100, 200, 300};
    private static int[] TRAVEL_DURATIONS = {20, 15, 10, 5};

    private int capacity = 40;
    private int travelDuration = 20;

    public Truck(Mission mission) {
        super(mission);
    }

    public boolean go(ArrayList<Tradable> tradables) throws CapacityExceededException {
        if (tradables.get(0) == null){
            return false;
        }
        super.addToList(tradables);
        this.trade(tradables);
        super.tradingObjects.clear();
        return true;
    }

    public void clearList() {
        super.tradingObjects.clear();
    }

    public void trade(ArrayList<Tradable> tradingObjects) {
        int income = 0;//= 0
        for (Tradable tradingObject : tradingObjects) {
            income += tradingObject.getSellCost();
        }
        mission.addMoney(income);
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
        this.mission.spendMoney(VEHICLE_UPGRADE_COSTS[level+1]);
        this.level++;
        travelDuration = TRAVEL_DURATIONS[this.level];
        mission.spendMoney(VEHICLE_UPGRADE_COSTS[this.level]);
        capacity = CAPACITYS[level];
    }


    @Override
    public void show() {

    }

    @Override
    public Position getPosition() {
        return this.position;
    }
}
