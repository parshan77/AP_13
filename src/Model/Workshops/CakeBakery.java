package Model.Workshops;

import Model.Player;
import Model.Warehouse;

public class CakeBakery extends Workshop {

    public CakeBakery(Player player, Warehouse warehouse) {
        super("CakeBakery", new String[]{"Flour", "Cookie"}, "Cake", player, warehouse);
    }

    @Override
    public void show() {

    }
}
