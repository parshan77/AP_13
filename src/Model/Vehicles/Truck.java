package Model.Vehicles;

import Exceptions.CapacityExceededException;
import Exceptions.NotFoundException;
import Exceptions.TradingListIsEmptyException;
import Interfaces.Storable;
import Model.Mission;

import java.util.ArrayList;

public class Truck extends Vehicle {
    private static int[] TRUCK_CAPACITIES = {40,60,90,120};
    private static int[] TRUCK_UPGRADE_COSTS = {100, 250, 600};
    private static int[] TRUCK_TRAVEL_DURATIONS = {6, 4, 2, 1};

    private ArrayList<Storable> tradingItems = new ArrayList<>();

    public Truck(Mission mission) {
        super(mission,TRUCK_UPGRADE_COSTS,TRUCK_CAPACITIES,TRUCK_TRAVEL_DURATIONS);

    }

    public void addToList(Storable object) throws CapacityExceededException {
        if (capacity - occupiedCapacity < object.getVolume())
            throw new CapacityExceededException();
        tradingItems.add(object);
        occupiedCapacity += object.getVolume();
    }

    public void discardFromList(String itemName) throws NotFoundException {
        Storable item = null;
        for (Storable tradingObject : tradingItems)
            if (tradingObject.getName().toLowerCase().equals(itemName.toLowerCase()))
                item = tradingObject;
        if (item == null)
            throw new NotFoundException();
        tradingItems.remove(item);
        occupiedCapacity -= item.getVolume();
    }

    public ArrayList<Storable> getList() {
        return new ArrayList<>(tradingItems);
    }

    public void clearList() {
        tradingItems.clear();
        occupiedCapacity = 0;
    }

    public void go() throws TradingListIsEmptyException {
        if (tradingItems.isEmpty()) throw new TradingListIsEmptyException();
        sell(tradingItems);
        clearList();
    }

    private void sell(ArrayList<Storable> tradingObjects) {
        int income = 0;
        for (Storable tradingObject : tradingObjects)
            income += tradingObject.getSellCost();
        mission.addMoney(income);
    }

    public void printInfo() {
        System.out.printf("Level : %d \n" , level);

        System.out.print("List :");
        for (Storable item : tradingItems) {
            System.out.print(item.getName());
        }
        System.out.println();
        System.out.println("Capacity : " + capacity);
        System.out.println("Occupied capacity : " + occupiedCapacity);
        System.out.println("Travel Duration : " + travelDuration);
        if (level < Vehicle.VEHICLE_MAX_LEVEL)
            System.out.println("Upgrade Cost : "+ TRUCK_UPGRADE_COSTS[level]);
        else
            System.out.println("Truck is at its max level.");
    }
}
