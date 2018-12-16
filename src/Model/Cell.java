package Model;

import Exceptions.CellNoPredatorFoundException;
import Exceptions.CellNoPreyFoundException;
import Interfaces.VisibleInMap;
import Model.Animals.*;
import Model.Products.Product;

import java.util.ArrayList;

public class Cell {
    private Plant plant;
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<VisibleInMap> allItems = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();

    public void addToCell(VisibleInMap object) {
        allItems.add(object);
        if (object instanceof Animal) {
            animals.add((Animal) object);
        } else if (object instanceof Plant) {
            if (plant == null) {
                plant = (Plant) object;
            }
        } else if (object instanceof Product) {
            products.add((Product)object);
        }
    }

    public ArrayList<Product> collectProducts() {
        return products;
        //todo:chejuri clear konam array listesho?
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

    public void discardObject(VisibleInMap obj) {
        allItems.remove(obj);

    }

}
