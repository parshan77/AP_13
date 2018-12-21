package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class SewingFactory extends Workshop {

    public SewingFactory(Mission mission, Warehouse warehouse) {
        super("SewingFactory", new String[]{"Cloth"}, "Dress", mission, warehouse);
    }

    @Override
    public void show() {

    }
}
