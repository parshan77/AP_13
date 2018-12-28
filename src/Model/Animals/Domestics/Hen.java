package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;


public class Hen extends Domestic {
    private static int HEN_HUNGRY_MOVING_PACE = 2;
    private static int HEN_NORMAL_PACE = 1;
    private static int HEN_BUY_COST = 200;

    public Hen(Map map, Direction direction, Position position) {
        super(map, direction, position,"Egg" , HEN_HUNGRY_MOVING_PACE);
        name = "Hen";
        pace = HEN_NORMAL_PACE;
    }

    public static int getBuyCost() {
        return HEN_BUY_COST;
    }

    @Override
    public void show() {

    }
}
