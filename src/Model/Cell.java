package Model;

import Exceptions.CellNoPredatorFoundException;
import Exceptions.CellNoPreyFoundException;
import Interfaces.VisibleInMap;
import Model.Animals.*;

import java.util.ArrayList;

public class Cell {
    private Plant plant;
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<VisibleInMap> items = new ArrayList<>();

    public void addToCell(VisibleInMap object) {
        items.add(object);
        if (object instanceof Animal) {
            animals.add((Animal) object);
        }
        if (object instanceof Plant) {
            if (plant == null) {
                plant = (Plant) object;
            }
        }

    }

    public Plant getPlant() {
        return plant;
    }

    public Prey getPrey() throws CellNoPreyFoundException {
        for (Animal animal : animals)
            if (animal instanceof Prey)
                return (Prey) animal;
        throw new CellNoPreyFoundException();
    }

    public Predator getPredator() throws CellNoPredatorFoundException {
        for (Animal animal : animals)
            if (animal instanceof Predator)
                return (Predator) animal;
        throw new CellNoPredatorFoundException();

    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void discardAnimal(Prey prey) {
        animals.remove(prey);
        items.remove(prey);
    }

}
