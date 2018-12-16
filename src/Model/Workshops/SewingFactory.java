package Model.Workshops;

import Model.Player;
import Model.Warehouse;

public class SewingFactory extends Workshop {

    public SewingFactory(Player player, Warehouse warehouse) {
        super("SewingFactory", new String[]{"Cloth"}, "Dress", player, warehouse);
    }

    @Override
    public void show() {

    }
}
