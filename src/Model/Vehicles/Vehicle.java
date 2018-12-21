package Model.Vehicles;

import Exceptions.VehicleMaxCapacityExceededException;
import Interfaces.*;
import Model.Mission;

import java.util.ArrayList;

public abstract class Vehicle implements Movable, Upgradable, VisibleInMap, VisibleOutOfMap {
    protected Mission mission;
    protected ArrayList<Tradable> tradingObjects = new ArrayList<>();
    protected int capacity;
    protected int occupiedCapacity = 0;

    public Vehicle(Mission mission, int capacity) {
        this.mission = mission;
        this.capacity = capacity;
    }

    public void addToList(Tradable object) throws VehicleMaxCapacityExceededException {
        if (occupiedCapacity < object.getVolume()) {
            throw new VehicleMaxCapacityExceededException();
        }
        tradingObjects.add(object);
        occupiedCapacity += object.getVolume();
    }

}
