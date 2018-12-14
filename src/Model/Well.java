package Model;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellMaxLevelExceeded;
import Exceptions.WellNotEnoughWaterException;
import Interfaces.Upgradable;
import Interfaces.Visible;

public class Well implements Visible, Upgradable {
    private double capacity = WELL_BEGINING_CAPACITY;       //capacity = meghdare abi ke alan darim
    private double maxCapacity = WELL_BEGINING_CAPACITY;
    private Player player;
    private int level = 1;

    //constants
    public static double WELL_BEGINING_CAPACITY = 100;
    public static long WELL_REFILL_COST = 20;
    public static int WELL_EXTRACT_WATER_COST = 0;
    public static int WELL_UPGRADE_COST = 100;
    public static int WELL_UPGRADE_ADDED_CAPACITY = 20;
    public static int WELL_MAX_LEVEL = 3;

    public Well(Player player) {
        this.player = player;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, WellMaxLevelExceeded {
        if (level == WELL_MAX_LEVEL)
            throw new WellMaxLevelExceeded();
        player.spendMoney(WELL_UPGRADE_COST);
        maxCapacity += WELL_UPGRADE_ADDED_CAPACITY;
        capacity = maxCapacity;
        level++;
    }

    @Override
    public void show() {
        //todo
    }

    public void refill() throws NotEnoughMoneyException {
        player.spendMoney(WELL_REFILL_COST);
        this.capacity = maxCapacity;
    }

    public void extractWater(double amount) throws NotEnoughMoneyException, WellNotEnoughWaterException {
        if (capacity < amount) throw new WellNotEnoughWaterException();
        player.spendMoney(WELL_EXTRACT_WATER_COST);
        this.capacity -= amount;
    }
}
