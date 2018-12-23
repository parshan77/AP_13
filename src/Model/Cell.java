package Model;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.*;
import Model.Products.Product;

import java.util.ArrayList;

public class Cell {
    private Position position;
    private Plant plant;
    private ArrayList<VisibleInMap> allItems = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cage> cages = new ArrayList<>();

    public Cell(Position position) {
        this.position = position;
    }

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
        allItems.removeAll(products);
        ArrayList<Product> productsCopy = (ArrayList) products.clone(); //todo:chi mige?
        products.clear();
        return productsCopy;
        //todo:in kaar mikone? --> in bayad tu animal zade beshe
    }

    public Plant getPlant() {
        return plant;
    }

    public Prey getPrey() {
        for (Animal animal : animals)
            if (animal instanceof Prey) return (Prey) animal;
        return null;
    }

    public Predator getPredator() {
        for (Animal animal : animals)
            if (animal instanceof Predator)
                return (Predator) animal;
        return null;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void discardAnimal(Animal animal) throws NotFoundException {
        if (!animals.contains(animal)) {
            throw new NotFoundException();
        }
        allItems.remove(animal);
        animals.remove(animal);
    }

    public void removePlant() throws NotFoundException {
        if (plant == null) {
            throw new NotFoundException();
        }
        plant = null;
    }

    public ArrayList<Cage> collectCages() throws NotFoundException {
        if (cages.isEmpty())
            throw new NotFoundException();
        return cages;
    }

    public void plantHere() {
        try {
            this.plant = new Plant(new Position(this.position.getRow(), this.position.getColumn()));
        } catch (NotValidCoordinatesException e) {
            e.printStackTrace();
        }
    }
}
