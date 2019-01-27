package Model;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;
import View.GamePlayView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Warehouse implements VisibleOutOfMap, Upgradable {

    private static int[] WAREHOUSE_CAPACITY = {1000, 150, 300, 600};
    private static int[] WAREHOUSE_UPGRADE_COST = {200, 300, 500};
    private static int WAREHOUSE_MAXLEVEL = 3;

    private int capacity = WAREHOUSE_CAPACITY[0];
    private int occupiedSpace = 0;
    private ArrayList<Storable> items = new ArrayList<>();    //todo:Intellij chi mige
    private int level = 0;
    private Mission mission;

    Warehouse(Mission mission) {
        this.mission = mission;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == WAREHOUSE_MAXLEVEL) throw new MaxLevelExceededException();
        mission.spendMoney(WAREHOUSE_UPGRADE_COST[level]);
        level++;
        capacity = WAREHOUSE_CAPACITY[level];
    }

    public int getLevel() {
        return level;
    }

    public void store(Storable object) throws CapacityExceededException, MissionCompletedException {
        int objectVolume = object.getVolume();
        int availableSpace = capacity - occupiedSpace;
        if (availableSpace < objectVolume)
            throw new CapacityExceededException();
        occupiedSpace += objectVolume;
        items.add(object);
        mission.getLevelRequirementsChecker().productIsAddedToMap(object);
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

    public void printInfo() {
        System.out.printf("Level : %d \n", level);
        System.out.printf("Capacity : %d \n", capacity);
        System.out.printf("Occupied Capacity: %d \n", occupiedSpace);
        System.out.print("Items : ");
        for (Storable item : items) {
            System.out.print(item.getName() + ", ");
        }
        System.out.println();

    }

    public boolean isFullyUpgraded() {
        return level == WAREHOUSE_MAXLEVEL;
    }

    public int getUpgradeCost() {
        if (level < WAREHOUSE_MAXLEVEL)
            return WAREHOUSE_UPGRADE_COST[level];
        else return -1;
    }
}
