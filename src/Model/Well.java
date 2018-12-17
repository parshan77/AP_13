package Model;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellIsNotEmptyException;
import Exceptions.WellMaxLevelExceeded;
import Exceptions.WellNotEnoughWaterException;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Interfaces.VisibleOutOfMap;

public class Well implements VisibleOutOfMap, Upgradable {
    private int capacity = WELL_CAPACITY[0];       //capacity = meghdare abi ke alan darim
    private int current_water_amount = capacity;
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
        level++;
        capacity = WELL_CAPACITY[level];
        current_water_amount = capacity;
    }

    @Override
    public void show() {
        //todo
    }

    public void refill() throws NotEnoughMoneyException, WellIsNotEmptyException {
        if (current_water_amount > 0) throw new WellIsNotEmptyException();
        player.spendMoney(WELL_REFILL_COST[level]);
        this.current_water_amount = capacity;
    }

    public void extractWater(int amount) throws WellNotEnoughWaterException {
        if (current_water_amount < amount) throw new WellNotEnoughWaterException();
        this.current_water_amount -= amount;
    }
}
