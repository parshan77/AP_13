package Model.Animals;

import Model.Direction;
import Model.Map;
import Model.Position;

public abstract class Prey extends Animal {
    public Prey(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }
}
