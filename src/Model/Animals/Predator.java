package Model.Animals;

import Model.Direction;
import Model.Position;
import Model.Screen.Map;


public abstract class Predator extends Animal{
    private Map map;

    public Predator(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void kill(Animal animal){

    }

}
