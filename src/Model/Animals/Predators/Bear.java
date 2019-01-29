package Model.Animals.Predators;

import Model.Animals.Predator;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;

public class Bear extends Predator {
    private static int BEAR_VOLUME = 15;
    private static int BEAR_SELL_COST = 700;
    private static int BEAR_PACE = 1;

    public Bear(Map map, Direction direction, Position position) {
        super(map, direction, position, BEAR_VOLUME, BEAR_SELL_COST);
        name = "Bear";
        pace = BEAR_PACE;
    }
}
