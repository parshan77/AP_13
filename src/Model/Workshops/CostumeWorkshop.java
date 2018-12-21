package Model.Workshops;

import Model.Mission;
import Model.Warehouse;

public class CostumeWorkshop extends Workshop {

    public CostumeWorkshop(String name, String[] inputsTypeName, String outputTypeName, Mission mission, Warehouse warehouse) {
        super(name, inputsTypeName, outputTypeName, mission, warehouse);
    }

    @Override
    public void show() {

    }
}
