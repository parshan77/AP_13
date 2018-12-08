package Model.Vehicles;

import Exceptions.VehicleMaxCapacityExceededException;
import Interfaces.Movable;
import Interfaces.Tradable;
import Model.User;

import java.util.ArrayList;

public abstract class Vehicle implements Movable {
    protected User user;
    protected ArrayList<Tradable> tradingObjects = new ArrayList<>();
    protected int capacity;
    protected int occupiedCapacity = 0;

    public Vehicle(User user, int capacity) {
        this.user = user;
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
