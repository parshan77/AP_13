package Model.Animals.Seekers;

import Model.Animals.Animal;
import Exceptions.NotFoundException;
import Model.Animals.Seeker;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Screen.Map;

public class Dog extends Seeker {

    public Dog(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void kill(Animal animal){
        try {
            map.discardAnimal(animal);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            map.discardAnimal(this);//todo: doroste??!
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void move() {
        for (int i = 0; i < pace; i++) {
            step();
        }
    }

    @Override
    public void step() {
        super.step();
        //todo: sharte barkhord barresi she...
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void show() {

    }
}
