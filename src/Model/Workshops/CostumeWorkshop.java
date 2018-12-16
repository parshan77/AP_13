package Model.Workshops;

import Model.Player;
import Model.Warehouse;

public class CostumeWorkshop extends Workshop {

    public CostumeWorkshop(String name, String[] inputsTypeName, String outputTypeName, Player player, Warehouse warehouse) {
        super(name, inputsTypeName, outputTypeName, player, warehouse);
    }

    @Override
    public void show() {

    }
}
