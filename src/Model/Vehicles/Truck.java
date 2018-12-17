package Model.Vehicles;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Tradable;
import Model.Direction;
import Model.Player;
import Model.Position;

import java.util.ArrayList;

public class Truck extends Vehicle {

    //connstants
    private static int TRUCK_CAPACITY = 20;
    private static int[] TRUCK_UPGRADE_COSTS = {100, 200, 300};
    private static int[] BOX_COUNTS = {2, 3, 4, 5};
    private static int[] TRAVEL_DURATION = {20, 15, 10, 5};//moddat zamane safar

    private int boxCount = 2;
    private int travelDuration = 20;

    //todo: Singleton
    private Truck(Player player) {
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
///////////////////////////////////////////////////////////////////////////////////
    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceeded {
        super.upgrade();
        boxCount = BOX_COUNTS[i];
        travelDuration = TRAVEL_DURATION[i];
        player.spendMoney(TRUCK_UPGRADE_COSTS[i]);
    }

    @Override
    public void show() {

    }

    @Override
    public Position getPosition() {
        return null;
    }
}
