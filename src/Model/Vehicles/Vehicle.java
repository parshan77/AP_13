package Model.Vehicles;

import Exceptions.VehicleMaxCapacityExceededException;
import Interfaces.*;
import Model.Player;

import java.util.ArrayList;

public abstract class Vehicle implements Movable, Upgradable, VisibleInMap, VisibleOutOfMap {
    protected Player player;
    protected ArrayList<Tradable> tradingObjects = new ArrayList<>();
    protected int capacity;
    protected int occupiedCapacity = 0;

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

}
