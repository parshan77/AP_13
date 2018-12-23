package Model.Animals;

import Model.Direction;
import Model.Map;
import Model.Position;

public abstract class Seeker extends Prey{
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
