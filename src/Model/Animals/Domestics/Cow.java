package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;

public class Cow extends Domestic {
    private static int COW_HUNGRY_MOVING_PACE = 2;
    private static int COW_NORMAL_PACE = 1;

    public Cow(Map map, Direction direction, Position position) {
        super(map, direction, position, "Milk", COW_HUNGRY_MOVING_PACE);
        name = "Cow";
        pace = COW_NORMAL_PACE;
    }

    @Override
    public void show() {

    }
}
