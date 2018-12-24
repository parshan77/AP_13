package Model.Vehicles;

import Exceptions.CapacityExceededException;
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
    private static int[] CAPACITYS = {40, 60, 100, 140};

    private int travelDuration = 12;
    private int scatteringRadius = 120;
    private Position position;
    private int capacity = 40;

    public Helicopter(Mission mission) {
        super(mission);
    }

    public boolean go(ArrayList<Tradable> tradables) throws NotEnoughMoneyException, CapacityExceededException {
        if (tradables.get(0) == null){
            return false;
        }
        super.addToList(tradables);
        this.buy(tradables);
        super.tradingObjects.clear();
        return true;
    }

    public void clearList() {
        super.tradingObjects.clear();
    }
    public void buy(ArrayList<Tradable> buyingList) throws NotEnoughMoneyException {
        int cost = 0;
        for (Tradable tradable : buyingList) {
            cost += tradable.getBuyCost();
        }
        mission.spendMoney(cost);

        //todo:chizayi ke kharidaro be mission nadadim
    }

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
        this.mission.spendMoney(VEHICLE_UPGRADE_COSTS[level+1]);
        this.level++;
        travelDuration = TRAVEL_DURATIONS[this.level];
        mission.spendMoney(VEHICLE_UPGRADE_COSTS[this.level]);
        scatteringRadius = SCATTERING_RADIUSES[this.level];
        capacity = CAPACITYS[level];
    }

    @Override
    public void show() {
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

}
