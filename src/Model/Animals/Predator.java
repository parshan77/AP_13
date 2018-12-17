package Model.Animals;

import Exceptions.CellNotExistsException;
import Model.Map;

public abstract class Predator extends Animal {
    private Map map;

    public Predator(Map map) {
        this.map = map;
    }

    public void kill(Animal animal){
        try {
            map.getCell(position.getRow(), position.getColumn()).discardObject(animal);
        } catch (CellNotExistsException e) {
            //todo: what to do??
        }
    }
}
