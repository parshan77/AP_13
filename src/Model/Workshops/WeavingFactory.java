package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class WeavingFactory extends Workshop {

    public WeavingFactory(Mission mission, Warehouse warehouse) {
        super("WeavingFactory", new String[]{"Fiber"}, "Cloth", mission,warehouse);
    }

    @Override
    public void show() {

    }
}
