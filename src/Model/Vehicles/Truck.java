package Model.Vehicles;

import Interfaces.Tradable;
import Model.Player;

import java.util.ArrayList;

public class Truck extends Vehicle {
    private static int TRUCK_CAPACITY;

    //todo: Singleton
    public Truck(Player player) {
        super(player, TRUCK_CAPACITY);
    }

    public void trade(ArrayList<Tradable> tradingObjects) {
        int income = 0 ;//= 0
        for (Tradable tradingObject : tradingObjects) {
            income += tradingObject.getSellCost();
        }
        player.addMoney(income);
        super.tradingObjects.clear();
    }
}
