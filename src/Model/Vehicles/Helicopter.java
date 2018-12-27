package Model.Vehicles;

import Exceptions.CapacityExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.NotFoundException;
import Exceptions.TradingListIsEmptyException;
import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Mission;
import Model.Placement.Position;
import Model.Products.Product;
import Utils.Utils;

import java.util.ArrayList;

public class Helicopter extends Vehicle {
    private static int[] HELICOPTER_UPGRADE_COSTS = {150, 300, 700};
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

    public void clearList() {
        tradingItems.clear();
        occupiedCapacity = 0;
    }

    public void go() throws NotEnoughMoneyException, TradingListIsEmptyException {
        if (tradingItems.isEmpty())
            throw new TradingListIsEmptyException();
        this.buy(tradingItems);
        this.putObjectsInMap(tradingItems);
        tradingItems.clear();
        occupiedCapacity = 0;
    }

    private void buy(ArrayList<Product> buyingList) throws NotEnoughMoneyException {
        int cost = 0;
        for (Storable storable : buyingList) {
            cost += storable.getBuyCost();
        }
        mission.spendMoney(cost);
    }

    private void putObjectsInMap(ArrayList<Product> tradingGoods) {
        Position position = Utils.getRandomPosition();
        for (Product item : tradingGoods) {
            item.setPosition(position);
            mission.getMap().addToMap(item);
        }
    }

    @Override
    public void show() {

    }
}
