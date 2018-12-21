package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class EggPowderPlant extends Workshop {

    public EggPowderPlant(Mission mission, Warehouse warehouse) {
        super("EggPowderPlant", new String[]{"Egg"}, "EggPowder", mission, warehouse);
    }

    @Override
    public void show() {

    }

}
