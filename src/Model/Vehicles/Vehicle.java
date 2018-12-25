package Model.Vehicles;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.CapacityExceededException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.*;
import Model.Placement.Direction;
import Model.Mission;
import Model.Placement.Position;

import java.util.ArrayList;

public abstract class Vehicle implements Movable, Upgradable, VisibleInMap, VisibleOutOfMap {
    protected Mission mission;
    protected ArrayList<Storable> tradingObjects = new ArrayList<>();
    protected int capacity;
    protected int occupiedCapacity = 0;
    protected static int VEHICLE_MAX_LEVEL = 3;

    protected int level = 0;
    protected Position position = new Position(511, 1023);      // yek makane khas rooye map ra behesh ekhtesas midim
    protected Direction direction = new Direction(0, 0);


    Vehicle(Mission mission) {
        this.mission = mission;
    }

    public void addToList(Storable object) throws CapacityExceededException {
        if (capacity-occupiedCapacity < object.getVolume())
            throw new CapacityExceededException();
        tradingObjects.add(object);
        occupiedCapacity += object.getVolume();
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (this.level == VEHICLE_MAX_LEVEL) {
            throw new MaxLevelExceededException();
        }
    }

}
