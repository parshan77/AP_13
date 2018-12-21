package Model.Animals.Seekers;

import Exceptions.CellNotExistsException;
import Model.Animals.Animal;
import Model.Animals.Seeker;
import Model.Direction;
import Model.Map;

public class Dog extends Seeker {
    private Map map;

    public Dog(Map map) {
        this.map = map;
    }

    public void kill(Animal animal){
        try {
            map.getCell(position.getRow(), position.getColumn()).discardObject(animal);
        } catch (CellNotExistsException e) {
            //todo: what to do??
        }
        try {
            map.getCell(position.getRow(), position.getColumn()).discardObject(this);//todo: doroste??!
        } catch (CellNotExistsException e) {
            //todo: what to do??
        }
    }
    @Override
    public void move() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void show() {

    }
}
