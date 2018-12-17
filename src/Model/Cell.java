package Model;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.*;
import Model.Products.Product;

import java.util.ArrayList;

public class Cell {
    private Plant plant;
    private ArrayList<VisibleInMap> allItems = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cage> cages = new ArrayList<>();

    public void addToCell(VisibleInMap obj) {
        allItems.add(obj);
        if (obj instanceof Animal) {
            animals.add((Animal) obj);
        } else if (obj instanceof Plant) {
            if (plant == null) {
                plant = (Plant) obj;
            }
        } else if (obj instanceof Product) {
            products.add((Product) obj);
        } else if (obj instanceof Cage) {
            cages.add((Cage) obj);
        }
    }

    public ArrayList<Product> collectProducts() {
        ArrayList<Product> productsCopy = (ArrayList) products.clone();
        products.clear();
        return productsCopy;
        //todo:in kaar mikone?
    }

    public Plant getPlant() throws CellNoPlantExistsException {
        if (plant == null)
            throw new CellNoPlantExistsException();
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

    public void discardAnimal(Animal animal) throws CellAnimalDiscardingException {
        if (!animals.contains(animal)) {
            throw new CellAnimalDiscardingException();
        }
        allItems.remove(animal);
        animals.remove(animal);
    }

    public void removePlant() throws CellPlantRemovingException {
        if (plant == null) {
            throw new CellPlantRemovingException();
        }
        plant = null;
    }

    public ArrayList<Cage> collectCages() throws CellNoCageExistsException {
        if (cages.isEmpty())
            throw new CellNoCageExistsException();
        return cages;
    }
}
