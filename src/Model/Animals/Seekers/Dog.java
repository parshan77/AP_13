package Model.Animals.Seekers;

import Exceptions.NotFoundException;
import Model.Animals.Animal;
import Model.Animals.Seeker;
import Model.Direction;
import Model.Screen.Map;

public class Dog extends Seeker {
    private Map map;

    public Dog(Map map) {
        this.map = map;
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

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void show() {

    }
}
