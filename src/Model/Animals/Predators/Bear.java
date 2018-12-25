package Model.Animals.Predators;

import Model.Animals.Animal;
import Model.Animals.Predator;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Screen.Map;

public class Bear extends Predator {
    public Bear(Map map, Direction direction, Position position) {
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
    public void kill(Animal animal) {
        super.kill(animal);
    }

    @Override
    public void show() {

    }
}
