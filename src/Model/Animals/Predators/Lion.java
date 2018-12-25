package Model.Animals.Predators;

import Model.Animals.Predator;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Screen.Map;

public class Lion extends Predator {
    public Lion(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void step() {
        super.step();
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void show() {

    }
}
