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
    private ArrayList<Predator> predators = new ArrayList<>();
    private ArrayList<Domestic> domestics = new ArrayList<>();
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Dog> dogs = new ArrayList<>();

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cage> cages = new ArrayList<>();

    Cell(Position position) {
        this.position = position;
    }

    void discardPlant() {
        this.plant = null;
    }

    void discardAnimal(Animal animal) throws NotFoundException {
        if (!animals.contains(animal)) {
            throw new NotFoundException();
        }
        allItemsInCell.remove(animal);
        animals.remove(animal);
        if (animal instanceof Predator) {
            predators.remove((Predator) animal);
        } else if (animal instanceof Domestic) {
            domestics.remove((Domestic) animal);
        }
        if (animal instanceof Dog) {
            dogs.remove((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.remove((Cat) animal);
        }
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
        return predators;
    }

    ArrayList<Domestic> getDoemstics() {
        return domestics;
    }

    ArrayList<Cat> getCats() {
        return cats;
    }

    ArrayList<Dog> getDogs() {
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

    void addToPredators(Predator predator) {
        predators.add(predator);
    }

    void addToDomestics(Domestic domestic) {
        //todo:
    }

    void addToDogs(Dog dog) {
        dogs.add(dog);
    }

    void addToCats(Cat cat) {
        cats.add(cat);
    }

    void addToProducts(Product product) {
        products.add(product);
    }

    void addPlant(Plant plant) throws PlantingFailureException {
        if (this.plant != null) throw new PlantingFailureException();
        this.plant = plant;
    }

    void addToCages(Cage cage) {
        cages.add(cage);
    }

    void clearProductsList() {
        products.clear();
    }
}
