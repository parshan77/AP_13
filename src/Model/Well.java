package Model;

import Exceptions.NotEnoughMoneyException;
import Exceptions.WellNotEnoughCapacityException;
import Interfaces.Visible;

public class Well implements Visible {
    private double capacity;
    private double MAX_CAPACITY;
    private long WELL_REFILL_COST;
    private long WELL_EXTRACT_WATER_COST;
    private User user;

    public Well(User user) {
        this.capacity = MAX_CAPACITY;
        this.user = user;
    }

    @Override
    public void show() {
        //todo
    }

    public void refill() throws NotEnoughMoneyException {
        capacity = MAX_CAPACITY;
        user.spendMoney(WELL_REFILL_COST);
    }

    public void extractWater(double amount) throws NotEnoughMoneyException, WellNotEnoughCapacityException {
        if (capacity < amount)
            throw new WellNotEnoughCapacityException();
        this.capacity -= amount;
        user.spendMoney(WELL_EXTRACT_WATER_COST);
    }
}
