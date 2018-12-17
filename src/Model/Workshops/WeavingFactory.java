package Model.Workshops;

import Model.Player;
import Model.Warehouse;

public class WeavingFactory extends Workshop {

    public WeavingFactory(Player player, Warehouse warehouse) {
        super("WeavingFactory", new String[]{"Fiber"}, "Cloth", player,warehouse);
    }

    @Override
    public void show() {

    }
}
