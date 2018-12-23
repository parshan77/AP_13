package Model.Animals;

import Exceptions.CellAnimalDiscardingException;
import Model.Map;

public abstract class Predator extends Animal {
    private Map map;

    public Predator(Map map) {
        this.map = map;
    }

    public void kill(Animal animal){
        try {
            map.getCell(position.getRow(), position.getColumn()).discardAnimal(animal);
        } catch (MapCellGettingException e) {
            //todo: what to do??
        } catch (CellAnimalDiscardingException e) {
            //todo: what to do??
        }
    }
}
