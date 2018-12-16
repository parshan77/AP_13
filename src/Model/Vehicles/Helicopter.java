package Model.Vehicles;

import Exceptions.MaxLevelExceeded;
import Exceptions.NotEnoughMoneyException;
import Exceptions.WorkshopMaxLevelExceeded;
import Interfaces.Tradable;
import Model.Direction;
import Model.Player;
import Model.Position;

import java.util.ArrayList;

public class Helicopter extends Vehicle {

    //constants
    private static int[] HELICOPTER_UPGRADE_COST = {150, 250, 350};
    private static int HELICOPTER_MAX_LEVEL = 3;
    private static int HELICOPTER_CAPACITY = 10;

    private Helicopter(Player player) {
        super(player, HELICOPTER_CAPACITY);
    }

    public void buy(ArrayList<Tradable> buyingList) throws NotEnoughMoneyException {
        int cost = 0;
        for (Tradable tradable : buyingList) {
            cost += tradable.getBuyCost();
        }
        player.spendMoney(cost);
        super.tradingObjects.clear();
        //todo:chizayi ke kharidaro be player nadadim
    }
///////////////////////////////////////////////////////////////////////////////////
    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceeded {

    }

    @Override
    public void show() {

    }

    @Override
    public Position getPosition() {
        return null;
    }
}
