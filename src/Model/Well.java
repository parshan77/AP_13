package Model;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellNotEnoughCapacityException;
import Interfaces.Visible;

public class Well implements Visible {
    private double capacity;
    private static final double Well_CAPACITY;
    private static final long WELL_REFILL_COST;
    private static final int WELL_EXTRACT_WATER_COST;
    private User user;

    public Well(User user) {
        this.capacity = Well_CAPACITY;
        this.user = user;
    }

    @Override
    public void show() {
        //todo
    }

    public void refill() throws NotEnoughMoneyException {
        capacity = Well_CAPACITY;
        user.spendMoney(WELL_REFILL_COST);
    }

    public void extractWater(double amount) throws NotEnoughMoneyException, WellNotEnoughCapacityException {
        if (capacity < amount)
            throw new WellNotEnoughCapacityException();
        this.capacity -= amount;
        user.spendMoney(WELL_EXTRACT_WATER_COST);
    }
}
