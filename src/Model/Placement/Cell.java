package Model.Placement;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.*;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Cage;
import Model.Plant;
import Model.Products.Cake;
import Model.Products.Product;

import java.util.ArrayList;

public class Cell {
    private Position position;
    private Plant plant;

    private ArrayList<VisibleInMap> items = new ArrayList<>();



    Cell(Position position) {
        this.position = position;
    }

    void discardPlant() {
        this.plant = null;
    }

    void discardAnimal(Animal animal) throws NotFoundException {
        if (!items.contains(animal)) throw new NotFoundException();
        items.remove(animal);
    }

    void discardCages(ArrayList<Cage> cages) {
        items.removeAll(cages);
    }

    void discardProducts(ArrayList<Product> products) {
        items.removeAll(products);
    }

    void discardItem(VisibleInMap item) {
        items.remove(item);
    }

    Position getPosition() {
        return position;
    }

    Plant getPlant() {
        return plant;
    }

    ArrayList<Animal> getAnimals() {
        ArrayList<Animal> animals = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Animal)
                animals.add((Animal) item);
        return animals;
    }

    ArrayList<Predator> getPredators() {
        ArrayList<Predator> predators = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Predator)
                predators.add((Predator) item);
        return predators;
    }

    ArrayList<Domestic> getDoemstics() {
        ArrayList<Domestic> domestics = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Domestic)
                domestics.add((Domestic) item);
        return domestics;
    }

    ArrayList<Cat> getCats() {
        ArrayList<Cat> cats = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Cat)
                cats.add((Cat) item);
        return cats;
    }

    ArrayList<Dog> getDogs() {
        ArrayList<Dog> dogs = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Dog)
                dogs.add((Dog) item);
        return dogs;
    }

    ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Product)
                products.add((Product) item);
        return products;
    }

    ArrayList<Cage> getCages() {
        ArrayList<Cage> cages = new ArrayList<>();
        for (VisibleInMap item : items)
            if (item instanceof Cage)
                cages.add((Cage) item);
        return cages;
    }

    void addItemToCell(VisibleInMap obj) {
        items.add(obj);
    }

    boolean addPlant(Plant plant)  {
        if (this.plant != null) return false;
        this.plant = plant;
        return true;
    }
}
