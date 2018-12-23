package Model.Animals;

import Model.Direction;
import Model.Position;
import Model.Screen.Map;

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
