package Model.Screen;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.*;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Cage;
import Model.Plant;
import Model.Position;
import Model.Products.Product;

import java.util.ArrayList;

public class Cell {
    private Position position;
    private Plant plant;

    private ArrayList<VisibleInMap> allItemsInCell = new ArrayList<>();
    private ArrayList<Animal> animals = new ArrayList<>();

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cage> cages = new ArrayList<>();

    Cell(Position position) {
        this.position = position;
    }

    void discardPlant() {
        this.plant = null;
    }

    void discardAnimal(Animal animal) throws NotFoundException {
        if (!animals.contains(animal)) throw new NotFoundException();
        allItemsInCell.remove(animal);
        animals.remove(animal);
    }

    Position getPosition() {
        return position;
    }

    Plant getPlant() {
        return plant;
    }

    ArrayList<Animal> getAnimals() {
        return animals;
    }

    ArrayList<Predator> getPredators() {
        ArrayList<Predator> predators = new ArrayList<>();
        for (Animal animal : animals)
            if (animal instanceof Predator)
                predators.add((Predator) animal);
        return predators;
    }

    ArrayList<Domestic> getDoemstics() {
        ArrayList<Domestic> domestics = new ArrayList<>();
        for (Animal animal : animals)
            if (animal instanceof Domestic)
                domestics.add((Domestic) animal);
        return domestics;
    }

    ArrayList<Cat> getCats() {
        ArrayList<Cat> cats = new ArrayList<>();
        for (Animal animal : animals)
            if (animal instanceof Cat)
                cats.add((Cat) animal);
        return cats;
    }

    ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogs = new ArrayList<>();
        for (Animal animal : animals)
            if (animal instanceof Dog)
                dogs.add((Dog) animal);
        return dogs;
    }

    ArrayList<Product> getProducts() {
        return products;
    }

    ArrayList<Cage> getCages() {
        return cages;
    }

    void addToCellAllItems(VisibleInMap obj) {
        allItemsInCell.add(obj);
    }

    void addToAnimals(Animal obj) {
        animals.add(obj);
    }

    void addToProducts(Product product) {
        products.add(product);
    }

    boolean addPlant(Plant plant)  {
        if (this.plant != null) return false;
        this.plant = plant;
        return true;
    }

    void addToCages(Cage cage) {
        cages.add(cage);
    }

    void clearProductsList() {
        products.clear();
    }
}
