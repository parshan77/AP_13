package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class Spinnery extends Workshop {

    public Spinnery(Mission mission, Warehouse warehouse) {
        super("Spinnery", new String[]{"Wool"}, "Fiber", mission, warehouse);
    }

    @Override
    public void show() {

    }
}
