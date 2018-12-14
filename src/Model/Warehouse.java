package Model;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.Visible;

import java.util.ArrayList;

public class Warehouse implements Visible, Upgradable {
    private int capacity = WAREHOUSE_BEGINNING_CAPACITY;
    private int occupiedCapacity = 0;
    private ArrayList<Storable> items = new ArrayList<>();    //todo:Intellij chi mige
    private int level = 1;
    private Player player;

    //constants
    public static int WAREHOUSE_BEGINNING_CAPACITY = 50;
    public static int WAREHOUSE_UPGRADE_COST = 300;
    public static int WAREHOUSE_UPGRADE_ADDED_CAPACITY = 10;
    public static int WAREHOUSE_MAXLEVEL;

    public Warehouse(Player player) {
        this.player = player;
    }

    @Override
    public void upgrade() throws WarehouseMaxLevelExceeded, NotEnoughMoneyException {
        if (level == WAREHOUSE_MAXLEVEL) throw new WarehouseMaxLevelExceeded();
        player.spendMoney(WAREHOUSE_UPGRADE_COST);
        level ++;
        capacity += WAREHOUSE_UPGRADE_ADDED_CAPACITY;
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

    //todo:in bayad ba generic zade beshe
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
