package Model.Vehicles;

import Interfaces.Tradable;
import Model.User;

import java.util.ArrayList;

public class Truck extends Vehicle {
    private User user;
    private ArrayList<Tradable> sellingList;

    public Truck(User user) {
        this.user = user;
        sellingList = new ArrayList<>();
    }

    public void addToSellingList(Tradable object) {
        sellingList.add(object);
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
