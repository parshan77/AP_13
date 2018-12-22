package Model.Vehicles;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.CapacityExceededException;
import Interfaces.*;
import Model.Mission;

import java.util.ArrayList;

public abstract class Vehicle implements Movable, Upgradable, VisibleInMap, VisibleOutOfMap {
    protected Mission mission;
    protected ArrayList<Tradable> tradingObjects = new ArrayList<>();
    protected int capacity;
    protected int occupiedCapacity = 0;
    protected int[] VEHICLE_UPGRADE_COSTS = new int[4] ;
    protected static int VEHICLE_MAX_LEVEL = 3;
    protected int[] TRAVEL_DURATIONS = new int[4];
    protected  int travelDuration;

    protected int level = 0;

    public Vehicle(Mission mission, int capacity) {
        this.mission = mission;
        this.capacity = capacity;
    }

    public void addToList(Tradable object) throws CapacityExceededException {
        if (occupiedCapacity < object.getVolume()) {
            throw new CapacityExceededException();
        }
        tradingObjects.add(object);
        occupiedCapacity += object.getVolume();
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
            if (this.level == VEHICLE_MAX_LEVEL){
                throw new MaxLevelExceededException();
            }
            if (this.mission.getMoney() <= VEHICLE_UPGRADE_COSTS[level+1]){
                throw new NotEnoughMoneyException();
            }
            level++;
            travelDuration = TRAVEL_DURATIONS[this.level];
            mission.spendMoney(VEHICLE_UPGRADE_COSTS[this.level]);
    }

}
