package Model;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.WellIsNotEmptyException;
import Exceptions.WellNotEnoughWaterException;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;

public class Well implements VisibleOutOfMap, Upgradable {

    //constants
    private static int[] WELL_CAPACITY = {5, 7, 10, 100};
    private static int[] WELL_REFILL_COST = {19, 17, 15, 7};
    private static int[] WELL_UPGRADE_COST = {150, 300, 500};
    private static int WELL_MAX_LEVEL = 3;
    private static int[] WELL_REFILLING_TIME = {5, 4, 3, 2};

    private int capacity = WELL_CAPACITY[0];
    private int current_water_amount = capacity;
    private Mission mission;
    private int level = 0;

    public Well(Mission mission) {
        this.mission = mission;
    }

    public int getRefillTime() {
        return WELL_REFILLING_TIME[level];
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == WELL_MAX_LEVEL)
            throw new MaxLevelExceededException();
        mission.spendMoney(WELL_UPGRADE_COST[level]);
        level++;
        capacity = WELL_CAPACITY[level];
        current_water_amount = capacity;
    }

    @Override
    public void show() {
    }

    public void refill() throws NotEnoughMoneyException, WellIsNotEmptyException {
        if (current_water_amount > 0) throw new WellIsNotEmptyException();
        mission.spendMoney(WELL_REFILL_COST[level]);
        this.current_water_amount = capacity;
    }

    public void extractWater(int amount) throws WellNotEnoughWaterException {
        if (current_water_amount < amount) throw new WellNotEnoughWaterException();
        this.current_water_amount -= amount;
    }

}
