package Model.Animals;

import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Placement.Map;

public abstract class Seeker extends Animal {
    Map map;

    public Seeker(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    @Override
    public void move() {

    }

    @Override
    public void step() {
        super.step();
    }
}
