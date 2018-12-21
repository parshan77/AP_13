package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class CookieBakery extends Workshop {

    public CookieBakery(Mission mission, Warehouse warehouse) {
        super("CookieBakery", new String[]{"EggPowder"}, "Cookie", mission, warehouse);
    }

    @Override
    public void show() {

    }
}
