package Model.Animals.Predators;

import Model.Animals.Predator;
import Model.Direction;
import Model.Position;
import Model.Screen.Map;

public class Lion extends Predator {
    public Lion(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void show() {

    }
}
