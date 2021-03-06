package Model.Vehicles;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;
import Model.Mission;
import View.GamePlayView;
import javafx.scene.image.ImageView;

public abstract class Vehicle implements Upgradable, VisibleOutOfMap {
    protected Mission mission;
    private int[] upgrade_costs;
    private int[] capacities;
    private int[] travelDurations;
    int occupiedCapacity = 0;
    static int VEHICLE_MAX_LEVEL = 3;
    int level = 0;
    int capacity;
    int travelDuration;

    Vehicle(Mission mission, int[] upgrade_costs, int[] capacities, int[] travelDurations) {
        this.mission = mission;
        this.upgrade_costs = upgrade_costs;
        this.capacities = capacities;
        this.travelDurations = travelDurations;
        capacity = capacities[0];
        travelDuration = travelDurations[0];
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (this.level == VEHICLE_MAX_LEVEL) throw new MaxLevelExceededException();
        mission.spendMoney(upgrade_costs[level]);
        level++;
        travelDuration = travelDurations[level];
        capacity = capacities[level];
    }

    public int getLevel() {
        return level;
    }

    public int getTravelDuration() {
        return travelDuration;
    }

    public int getUpgradeCost() {
        if (level < VEHICLE_MAX_LEVEL)
            return upgrade_costs[level];
        return -1;
    }

    public boolean isFullyUpgraded() {
        return level == VEHICLE_MAX_LEVEL;
    }
}
