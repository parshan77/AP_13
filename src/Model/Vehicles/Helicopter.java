package Model.Vehicles;

import Exceptions.NotEnoughMoneyException;
import Interfaces.Tradable;
import Model.Player;

import java.util.ArrayList;

public class Helicopter extends Vehicle {
    private static int HELICOPTER_CAPACITY;

    //todo: Singleton
    public Helicopter(Player player) {
        super(player,HELICOPTER_CAPACITY);
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

}
