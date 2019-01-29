package Model.Vehicles;

import Exceptions.CapacityExceededException;
import Exceptions.NotFoundException;
import Exceptions.TradingListIsEmptyException;
import Model.Mission;
import Model.Placement.Position;
import Model.Products.Product;
import Utils.Utils;

import java.util.ArrayList;

public class Helicopter extends Vehicle {
    private static int[] HELICOPTER_UPGRADE_COSTS = {150, 400, 900};
    private static int[] HELICOPTER_TRAVEL_DURATIONS = {9, 7, 4, 1};
    private static int[] HELICOPTER_CAPACITIES = {40, 60, 100, 140};
    private ArrayList<Product> tradingItems = new ArrayList<>();

    public Helicopter(Mission mission) {
        super(mission, HELICOPTER_UPGRADE_COSTS, HELICOPTER_CAPACITIES, HELICOPTER_TRAVEL_DURATIONS);
    }

    public void addToList(Product object) throws CapacityExceededException {
        if (capacity - occupiedCapacity < object.getVolume())
            throw new CapacityExceededException();
        tradingItems.add(object);
        occupiedCapacity += object.getVolume();
    }

    public void discardFromList(String itemName) throws NotFoundException {
        Product item = null;
        for (Product tradingObject : tradingItems)
            if (tradingObject.getName().toLowerCase().equals(itemName.toLowerCase()))
                item = tradingObject;
        if (item == null)
            throw new NotFoundException();
        tradingItems.remove(item);
        occupiedCapacity -= item.getVolume();
    }

    public ArrayList<Product> getList() {
        return new ArrayList<>(tradingItems);
    }

    public void clearList() {
        tradingItems.clear();
        occupiedCapacity = 0;
    }

    public int calculateCost() {
        int cost = 0;
        for (Product item : tradingItems) {
            cost += item.getBuyCost();
        }
        return cost;
    }

    public void go() throws TradingListIsEmptyException {
        if (tradingItems.isEmpty())
            throw new TradingListIsEmptyException();
        this.putProductsInMap(tradingItems);
        tradingItems.clear();
        occupiedCapacity = 0;
    }

    private void putProductsInMap(ArrayList<Product> tradingGoods) {
        Position position;
        for (Product item : tradingGoods) {
            position = Utils.getRandomPosition();
            item.setPosition(position);
            mission.getMap().addToMap(item);
        }
    }

    public void printInfo() {
        System.out.printf("Level : %d \n" , level);
        System.out.print("List :");
        for (Product item : tradingItems) {
            System.out.print(item.getName()+", ");
        }
        System.out.println();
        System.out.println("Capacity : " + capacity);
        System.out.println("Travel Duration : " + travelDuration);
        if (level < Vehicle.VEHICLE_MAX_LEVEL)
            System.out.println("Upgrade Cost : "+ HELICOPTER_UPGRADE_COSTS[level]);
        else
            System.out.println("Helicopter is at its max level.");
    }
}
