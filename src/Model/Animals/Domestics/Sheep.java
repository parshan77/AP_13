package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;

public class Sheep extends Domestic {
    private static int SHEEP_HUNGRY_MOVING_PACE = 2;
    private static int SHEEP_NORMAL_PACE = 1;
    private static int SHEEP_BUY_COST = 500;

    public Sheep(Map map, Direction direction, Position position) {
        super(map, direction, position,"Wool",SHEEP_HUNGRY_MOVING_PACE);
        name = "Sheep";
        pace = SHEEP_NORMAL_PACE;
    }

    public static int getBuyCost() {
        return SHEEP_BUY_COST;
    }

    @Override
    public void show() {

    }
}
