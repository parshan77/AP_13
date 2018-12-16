package Model;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Interfaces.VisibleOutOfMap;

import java.util.ArrayList;

public class Warehouse implements VisibleOutOfMap, Upgradable {
    private int capacity = WAREHOUSE_CAPACITY[0];
    private int occupiedCapacity = 0;
    private ArrayList<Storable> items = new ArrayList<>();    //todo:Intellij chi mige
    private int level = 0;
    private Player player;

    //constants
    public static int[] WAREHOUSE_CAPACITY = {50, 150, 300, 600};
    public static int[] WAREHOUSE_UPGRADE_COST = {200,300,400};
    public static int WAREHOUSE_MAXLEVEL = 3;

    public Warehouse(Player player) {
        this.player = player;
    }

    @Override
    public void upgrade() throws WarehouseMaxLevelExceeded, NotEnoughMoneyException {
        if (level == WAREHOUSE_MAXLEVEL) throw new WarehouseMaxLevelExceeded();
        player.spendMoney(WAREHOUSE_UPGRADE_COST[level]);
        level ++;
        capacity += WAREHOUSE_CAPACITY[level];
    }

    @Override
    public void show() {
    }

    public void store(Storable object) throws WarehouseNotEnoughCapacityException {
        int objectVolume = object.getVolume();
        if (capacity - occupiedCapacity < objectVolume)
            throw new WarehouseNotEnoughCapacityException();
        occupiedCapacity += objectVolume;
        items.add(object);
    }

    public Storable get(Storable object) throws WarehouseNoSuchStuffException {
        for (Storable item : items) {
            if (item.getClass().getName().equals(object.getClass().getName())) {
                occupiedCapacity -= item.getVolume();
                items.remove(object);
                return object;
            }
        }
        throw new WarehouseNoSuchStuffException();
    }
}
