package Model.Workshops;

import Model.Player;
import Model.Products.Flour;
import Model.Products.Egg;
import Model.Warehouse;

import java.util.ArrayList;

public class EggPowderPlant extends Workshop {

    public EggPowderPlant(Player player, Warehouse warehouse) {
        super("EggPowderPlant", new String[]{"Egg"}, "EggPowder", player, warehouse);
    }

    @Override
    public void show() {

    }

}
