package Model.Vehicles;

import Exceptions.NotEnoughMoneyException;
import Interfaces.Tradable;
import Model.User;

import java.util.ArrayList;

public class Helicopter extends Vehicle {
    private static int HELICOPTER_CAPACITY;

    public Helicopter(User user) {
        super(user,HELICOPTER_CAPACITY);
    }

    public void buy(ArrayList<Tradable> buyingList) throws NotEnoughMoneyException {
        int cost = 0;
        for (Tradable tradable : buyingList) {
            cost += tradable.getBuyingPrice();
        }
        user.spendMoney(cost);
        //todo:chizayi ke kharidaro behesh nadadim
    }

}
