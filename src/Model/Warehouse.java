package Model;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;

import java.util.ArrayList;

public class Warehouse implements VisibleOutOfMap, Upgradable {

    //constants
    private static int[] WAREHOUSE_CAPACITY = {50, 150, 300, 600};
    private static int[] WAREHOUSE_UPGRADE_COST = {200, 300, 500};
    private static int WAREHOUSE_MAXLEVEL = 3;

    private int capacity = WAREHOUSE_CAPACITY[0];
    private int occupiedSpace = 0;
    private ArrayList<Storable> items = new ArrayList<>();    //todo:Intellij chi mige
    private int level = 0;
    private Mission mission;
//    private Position position;


    public Warehouse(Mission mission) {
        this.mission = mission;
    }

    @Override
    public void upgrade() throws MaxLevelExceededException, NotEnoughMoneyException {
        if (level == WAREHOUSE_MAXLEVEL) throw new MaxLevelExceededException();
        mission.spendMoney(WAREHOUSE_UPGRADE_COST[level]);
        level++;
        capacity = WAREHOUSE_CAPACITY[level];
    }

    @Override
    public void show() {
    }

    public void store(Storable object) throws CapacityExceededException, LevelFinishedException {
        int objectVolume = object.getVolume();
        int availableSpace = capacity - occupiedSpace;
        if (availableSpace < objectVolume)
            throw new CapacityExceededException();
        occupiedSpace += objectVolume;
        items.add(object);
        mission.getLevelRequirementsChecker().updateRequirements(object);
    }

    public Storable getAndDiscard(String itemName) throws NotFoundException {
        Storable removedItem = null;
        for (Storable item : items)
            if (item.getName().toLowerCase().equals(itemName.toLowerCase())) {
                removedItem = item;
                break;
            }
        if (removedItem == null) throw new NotFoundException();

        items.remove(removedItem);
        occupiedSpace -= removedItem.getVolume();

        return removedItem;
    }
}
