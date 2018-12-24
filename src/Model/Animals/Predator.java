package Model.Animals;

import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Placement.Map;


public abstract class Predator extends Animal{
    private Map map;

    public Predator(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void kill(Animal animal){

    }

}
