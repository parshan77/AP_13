package Model.Vehicles;

import Interfaces.Tradable;
import Model.User;

import java.util.ArrayList;

public class Helicopter extends Vehicle {
    private User user;
    private ArrayList<Tradable> buyingList;

    public Helicopter(User user) {
        this.user = user;
        buyingList = new ArrayList<>();
    }

    public void addToBuyingList(Tradable object) {
        buyingList.add(object);
    }

    @Override
    public void move() {
        //todo
    }

    @Override
    public void show() {
        //todo
    }
}
