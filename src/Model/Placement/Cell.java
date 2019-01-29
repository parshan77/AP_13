package Model.Placement;

import Exceptions.NotFoundException;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Cage;
import Model.Plant;
import Model.Products.Product;

import java.util.ArrayList;

public class Cell {
    private Position position;
    private PositionInScene coordinates;        // TODO: 1/25/2019 be constructor ezafe she
    private int x;
    private int y;
    private Plant plant;
    private ArrayList<VisibleInMap> items = new ArrayList<>();

    Cell(Position position) {
        this.position = position;
    }

    public void setCoordinates(PositionInScene coordinates) {
        this.coordinates = coordinates;
    }

    public PositionInScene getCoordinates() {
        return coordinates;
    }

    void discardFromCell(VisibleInMap obj) throws NotFoundException {
        if (!items.contains(obj))
            throw new NotFoundException();
        items.remove(obj);
    }

    void addToCell(VisibleInMap obj) {
        items.add(obj);
        if (obj instanceof Plant)
            this.plant = (Plant) obj;
    }

    void discardPlant() {
        items.remove(plant);
        this.plant = null;
    }

    boolean isplanted() {
        return plant != null;
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

    ArrayList<Domestic> getDomestics() {
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

    void print() {
        for (VisibleInMap item : items) {
            System.out.print(item.getName() + " ");
        }
    }
}
