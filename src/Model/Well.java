package Model;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Exceptions.WellIsNotEmptyException;
import Exceptions.WellNotEnoughWaterException;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;
import Model.TimeDependentRequests.RefillWellRequest;
import View.Animations.WellExtractingAnimation;
import javafx.animation.Animation;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Well implements VisibleOutOfMap, Upgradable {

    //constants
    private static int[] WELL_CAPACITY = {5, 7, 10, 100};
    private static int[] WELL_REFILL_COST = {19, 17, 15, 7};
    private static int[] WELL_UPGRADE_COST = {120, 500, 1000};
    private static int WELL_MAX_LEVEL = 3;
    private static int[] WELL_REFILLING_TIME = {5, 4, 3, 2};

    private static int turnTime = 1000;     // TODO: 1/26/2019 ino bayad faghat yebar tanzim konim

    private int capacity = WELL_CAPACITY[0];
    private int current_water_amount = capacity;
    private Mission mission;
    private int level = 0;

    Well(Mission mission) {
        this.mission = mission;
    }

    public boolean isEmpty() {
        return current_water_amount == 0;
    }

    public int getRefillCost() {
        return WELL_REFILL_COST[level];
    }

    public int getRefillTime() {
        return WELL_REFILLING_TIME[level];
    }

    public int getCapacity() {
        return capacity;
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

    public void refill() {
        this.current_water_amount = capacity;
    }

    public void getWater(int amount) throws WellNotEnoughWaterException {
        if (current_water_amount < amount) throw new WellNotEnoughWaterException();
        this.current_water_amount -= amount;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeCost() {
        return WELL_UPGRADE_COST[level];
    }

    public boolean isFullyUpgraded() {
        return level == WELL_MAX_LEVEL;
    }
}
