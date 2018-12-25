package Model.Animals;

import Exceptions.NotFoundException;
import Model.Screen.Map;
import Model.Placement.Direction;
import Model.Placement.Position;


public abstract class Predator extends Animal{
    private Map map;

    public Predator(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void kill(Animal animal){
        try {
            map.discardAnimal(animal);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void step() {
        super.step();
        if (map.)
    }
}
