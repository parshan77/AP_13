package Model.Vehicles;

import Exceptions.VehicleNotEnoughCapacityException;
import Interfaces.Tradable;
import Model.User;

import java.util.ArrayList;

public class Truck extends Vehicle {
    private static int TRUCK_CAPACITY;

    public Truck(User user) {
        super(user, TRUCK_CAPACITY);
    }

    public void trade(ArrayList<Tradable> tradingObjects) {

    }
}
