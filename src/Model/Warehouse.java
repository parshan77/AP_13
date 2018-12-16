package Model;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;

import java.util.ArrayList;

public class Warehouse implements VisibleOutOfMap, Upgradable {
    private int capacity = WAREHOUSE_CAPACITY[0];
    private int occupiedSpace = 0;
    private ArrayList<Storable> items = new ArrayList<>();    //todo:Intellij chi mige
    private int level = 0;
    private Player player;
    private Position position;      //todo: chon jash sabete haminja initializesh kon
    //constants
    private static int[] WAREHOUSE_CAPACITY = {50, 150, 300, 600};
    private static int[] WAREHOUSE_UPGRADE_COST = {200, 300, 400};
    private static int WAREHOUSE_MAXLEVEL = 3;

    public Warehouse(Player player) {
        this.player = player;
    }

    @Override
    public void upgrade() throws WarehouseMaxLevelExceeded, NotEnoughMoneyException {
        if (level == WAREHOUSE_MAXLEVEL) throw new WarehouseMaxLevelExceeded();
        player.spendMoney(WAREHOUSE_UPGRADE_COST[level]);
        level++;
        capacity += WAREHOUSE_CAPACITY[level];
    }

    @Override
    public void show() {
    }

    public void store(Storable object) throws WarehouseNotEnoughCapacityException {
        int objectVolume = object.getVolume();
        if (capacity - occupiedSpace < objectVolume)
            throw new WarehouseNotEnoughCapacityException();
        occupiedSpace += objectVolume;
        items.add(object);
    }

    public Storable get(String itemName) throws WarehouseNoSuchStuffException {
        Storable removedItem = null;
        for (Storable item : items) {
            if (item.getName().equals(itemName)) {
                removedItem = item;
                break;
            }
        }
        if (removedItem == null)
            throw new WarehouseNoSuchStuffException();
        items.remove(removedItem);
        occupiedSpace -= removedItem.getVolume();
        return removedItem;
    }
}
