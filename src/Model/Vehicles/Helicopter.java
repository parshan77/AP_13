package Model.Vehicles;

import Exceptions.CapacityExceededException;
import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Placement.Direction;
import Model.Mission;
import Model.Placement.Position;

import java.util.ArrayList;
import java.util.Random;

public class Helicopter extends Vehicle {

    //constants
    private static int[] VEHICLE_UPGRADE_COSTS = {150, 250, 350};
    private static int[] TRAVEL_DURATIONS = {12, 9, 6, 3};
    private static int[] CAPACITYS = {40, 60, 100, 140};

    private int travelDuration = 12;
    private Position position;
    private int capacity = 40;

    public Helicopter(Mission mission) {
        super(mission);
    }

    public boolean go() throws NotEnoughMoneyException, CapacityExceededException {
        if (tradingObjects.get(0) == null){
            return false;
        }
        this.buy(tradingObjects);
        this.move();
        this.giveObjectsToMap(tradingObjects);
        super.tradingObjects.clear();
        super.occupiedCapacity = 0;
        return true;
    }
    public void giveObjectsToMap(ArrayList<Storable> tradables){
        Random random = new Random();
        for (Storable storables: tradables){
            this.mission.getMap().addToMap((VisibleInMap) storables);
        }

    }

    public void clearList() {
        super.tradingObjects.clear();
    }
    public void buy(ArrayList<Storable> buyingList) throws NotEnoughMoneyException {
        int cost = 0;
        for (Storable storable : buyingList) {
            cost += storable.getBuyCost();
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
        this.capacity = this.CAPACITYS[level];
    }

    @Override
    public void show() {
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

}
