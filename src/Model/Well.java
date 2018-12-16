package Model;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellMaxLevelExceeded;
import Exceptions.WellNotEnoughWaterException;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Interfaces.VisibleOutOfMap;

public class Well implements VisibleOutOfMap, Upgradable {
    private double capacity = WELL_CAPACITY[0];       //capacity = meghdare abi ke alan darim
    private double maxCapacity = WELL_CAPACITY[0];
    private Player player;
    private int level = 0;

    //constants
    private static int[] WELL_CAPACITY = {5, 7, 10, 100};
    private static int[] WELL_REFILL_COST = {19, 17, 15, 7};
    private static int WELL_UPGRADE_COST = 100;
    private static int WELL_MAX_LEVEL = 3;

    public Well(Player player) {
        this.player = player;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, WellMaxLevelExceeded {
        if (level == WELL_MAX_LEVEL)
            throw new WellMaxLevelExceeded();
        player.spendMoney(WELL_UPGRADE_COST);
        level ++;
        maxCapacity = WELL_CAPACITY[level];
        capacity = maxCapacity;
    }

    @Override
    public void show() {
        //todo
    }

    public void refill() throws NotEnoughMoneyException {
        player.spendMoney(WELL_REFILL_COST[level]);
        this.capacity = maxCapacity;
    }

    public void extractWater(int amount) throws WellNotEnoughWaterException {
        if (capacity < amount) throw new WellNotEnoughWaterException();
        this.capacity -= amount;
    }
}
