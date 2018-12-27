package Model.Animals;

import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;


public abstract class Seeker extends Animal {

    public Seeker(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

}
