package Model.Vehicles;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Tradable;
import Model.Direction;
import Model.Mission;
import Model.Position;

import java.util.ArrayList;

public class Helicopter extends Vehicle {

    //constants
    private static int[] VEHICLE_UPGRADE_COSTS = {150, 250, 350};
    private static int[] TRAVEL_DURATIONS = {12, 9, 6, 3};
    private static int[] SCATTERING_RADIUSES = {120, 100, 60, 20};
    private static int HELICOPTER_CAPACITY = 10;

    private int travelDuration = 12;
    private int scatteringRadius = 120;

    private Helicopter(Mission mission) {
        super(mission, HELICOPTER_CAPACITY);
    }

    public void buy(ArrayList<Tradable> buyingList) throws NotEnoughMoneyException {
        int cost = 0;
        for (Tradable tradable : buyingList) {
            cost += tradable.getBuyCost();
        }
        mission.spendMoney(cost);
        super.tradingObjects.clear();
        //todo:chizayi ke kharidaro be mission nadadim
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
        scatteringRadius = SCATTERING_RADIUSES[this.level];
    }

    @Override
    public void show() {

    }

    @Override
    public Position getPosition() {
        return null;
    }
}
