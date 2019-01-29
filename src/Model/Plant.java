package Model;

import Interfaces.VisibleInMap;
import Model.Placement.Position;

public class Plant implements VisibleInMap {
    private Position position;
    private String name = "Plant";

    //constants
    public static int PLANTING_NEEDED_WATER = 1;

    public Plant(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return name;
    }
}
