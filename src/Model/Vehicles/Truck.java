package Model.Vehicles;

import Interfaces.Tradable;
import Model.User;

import java.util.ArrayList;

public class Truck extends Vehicle {
    private static int TRUCK_CAPACITY;

    //todo: Singleton
    public Truck(User user) {
        super(user, TRUCK_CAPACITY);
    }

    public void trade(ArrayList<Tradable> tradingObjects) {
        int income = 0 ;//= 0
        for (Tradable tradingObject : tradingObjects) {
            income += tradingObject.getSellingPrice();
        }
        user.addMoney(income);
        super.tradingObjects.clear();
    }
}
