package Model.Animals;

import Exceptions.NotFoundException;
import Model.Screen.Map;

public abstract class Predator extends Animal {
    private Map map;

    public Predator(Map map) {
        this.map = map;
    }

    public void kill(Animal animal){
        try {
            map.discardAnimal(animal);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
