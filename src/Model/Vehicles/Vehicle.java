package Model.Vehicles;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Exceptions.VehicleMaxCapacityExceededException;
import Exceptions.WorkshopMaxLevelExceeded;
import Interfaces.*;
import Model.Player;

import java.util.ArrayList;

public abstract class Vehicle implements Movable, Upgradable, VisibleInMap, VisibleOutOfMap {
    protected Player player;
    protected ArrayList<Tradable> tradingObjects = new ArrayList<>();
    protected int capacity;
    protected int occupiedCapacity = 0;
    protected int[] VEHICLE_UPGRADE_COSTS = new int[4] ;
    protected static int VEHICLE_MAX_LEVEL = 3;
    protected int[] TRAVEL_DURATIONS = new int[4];
    protected  int travelDuration;

    protected int level = 0;

    public Vehicle(Player player, int capacity) {
        this.player = player;
        this.capacity = capacity;
    }

    public void addToList(Tradable object) throws VehicleMaxCapacityExceededException {
        if (occupiedCapacity < object.getVolume()) {
            throw new VehicleMaxCapacityExceededException();
        }
        tradingObjects.add(object);
        occupiedCapacity += object.getVolume();
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceeded {
            if (this.level == VEHICLE_MAX_LEVEL){
                throw new MaxLevelExceeded();
            }
            if (this.player.getMoney() <= VEHICLE_UPGRADE_COSTS[level+1]){
                throw new NotEnoughMoneyException();
            }
            level++;
            travelDuration = TRAVEL_DURATIONS[this.level];
            player.spendMoney(VEHICLE_UPGRADE_COSTS[this.level]);
    }

}
