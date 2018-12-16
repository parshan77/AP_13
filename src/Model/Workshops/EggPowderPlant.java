package Model.Workshops;

import Model.Player;
import Model.Products.Flour;
import Model.Products.Egg;
import Model.Warehouse;

import java.util.ArrayList;

public class EggPowderPlant extends Workshop<Egg,Flour> {

    public EggPowderPlant(Warehouse warehouse, Class inputClazz, Class outputClazz, Player player) {
        super(warehouse, inputClazz, outputClazz, player);
        name = "EggPowderPlant";
    }

    @Override
    public void show() {

    }

}
