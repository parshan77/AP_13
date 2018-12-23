package Model.Animals.Seekers;

import Model.Animals.Animal;
import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Model.Animals.Seeker;
import Model.Direction;
import Model.Map;
import Model.Position;

public class Dog extends Seeker {
    private Map map;

    public Dog(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    public void kill(Animal animal){
        try {
            map.getCell(position.getRow(), position.getColumn()).discardAnimal(animal);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NotValidCoordinatesException e) {
            e.printStackTrace();
        }
        try {
            map.getCell(position.getRow(), position.getColumn()).discardAnimal(this);//todo: doroste??!
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NotValidCoordinatesException e) {
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
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void show() {

    }
}
