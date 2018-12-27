package Model.Animals.Predators;

import Model.Animals.Predator;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;


public class Lion extends Predator {
    private static int LION_VOLUME = 10;
    private static int LION_SELL_COST = 500;
    private static int LION_PACE = 1;

    public Lion(Map map, Direction direction, Position position) {
        super(map, direction, position, LION_VOLUME, LION_SELL_COST);
        name = "Lion";
        pace = LION_PACE;
    }

    @Override
    public void show() {

    }

}
