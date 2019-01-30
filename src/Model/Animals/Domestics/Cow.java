package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;

public class Cow extends Domestic {
    private static int COW_HUNGRY_MOVING_PACE = 1;
    private static int COW_NORMAL_PACE = 1;
    private static int COW_BUY_COST = 1000;

    public Cow(Map map, Direction direction, Position position) {
        super(map, direction, position, "Milk", COW_HUNGRY_MOVING_PACE);
        name = "Cow";
        pace = COW_NORMAL_PACE;
    }

    public static int getBuyCost() {
        return COW_BUY_COST;
    }

}
