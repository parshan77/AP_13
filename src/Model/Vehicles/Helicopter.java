package Model.Vehicles;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.Tradable;
import Model.Placement.Direction;
import Model.Mission;
import Model.Placement.Position;

import java.util.ArrayList;

public class Helicopter extends Vehicle {

    //constants
    private static int[] VEHICLE_UPGRADE_COSTS = {150, 250, 350};
    private static int[] TRAVEL_DURATIONS = {12, 9, 6, 3};
    private static int[] SCATTERING_RADIUSES = {120, 100, 60, 20};
    private static int HELICOPTER_CAPACITY = 10;

    private static int travelDuration = 12;
    private static int scatteringRadius = 120;
    private Position position;

    public Helicopter(Mission mission) {
        super(mission, HELICOPTER_CAPACITY);
    }

    public boolean go() {
        return true;
        //todo
    }

    public void clearList() {
        //todo
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
        return this.direction;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        super.upgrade();
        scatteringRadius = SCATTERING_RADIUSES[this.level];
    }

    @Override
    public void show() {
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

}
