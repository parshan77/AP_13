package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class CakeBakery extends Workshop {

    public CakeBakery(Mission mission, Warehouse warehouse) {
        super("CakeBakery", new String[]{"Flour", "Cookie"}, "Cake", mission, warehouse);
    }

    @Override
    public void show() {

    }
}
