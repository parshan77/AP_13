package Model.Workshops;

import Model.Player;
import Model.Warehouse;

public class Spinnery extends Workshop {

    public Spinnery(Player player, Warehouse warehouse) {
        super("Spinnery", new String[]{"Wool"}, "Fiber", player, warehouse);
    }

    @Override
    public void show() {

    }
}
