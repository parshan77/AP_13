package Model.Placement;

import Exceptions.MissionCompletedException;
import Exceptions.NotFoundException;
import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Cage;
import Model.Mission;
import Model.Plant;
import Model.Products.Product;
import Utils.Utils;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;
    public static int MAP_SIZE = 14;
    private Mission mission;

    private ArrayList<VisibleInMap> allItemsInMap = new ArrayList<>();

    // TODO: 12/27/2018 intellij chert mige rajebe array lista chera?
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Predator> predators = new ArrayList<>();
    private ArrayList<Domestic> domestics = new ArrayList<>();
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Dog> dogs = new ArrayList<>();

    private ArrayList<Plant> plants = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cage> cages = new ArrayList<>();

    public Map(Mission mission) {
        this.mission = mission;
        cells = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < MAP_SIZE; j++)
                cells.get(i).add(new Cell(new Position(i, j)));
        }
    }

    public void discardOneProductFromCell(Product product) throws NotFoundException {
        int row = product.getPosition().getRow();
        int column = product.getPosition().getColumn();
        cells.get(row).get(column).discardFromCell(product);
        products.remove(product);
    }

    public Cell getCell(int row, int column) {
        return cells.get(row).get(column);
    }

    public void addToMap(VisibleInMap obj) {
        int row = obj.getPosition().getRow();
        int column = obj.getPosition().getColumn();
        Cell cell = cells.get(row).get(column);

        cell.addToCell(obj);
        allItemsInMap.add(obj);

        if (obj instanceof Animal) {
            animals.add((Animal) obj);

            if (obj instanceof Predator)
                predators.add((Predator) obj);
            else if (obj instanceof Domestic) {
                domestics.add((Domestic) obj);
                try {
                    mission.getLevelRequirementsChecker().domesticIsAddedToMap((Domestic) obj);
                } catch (MissionCompletedException e) {
                    mission.setMissionAsCompleted();    // TODO: 12/28/2018 harbar bayad in check beshe
                }
            }
            if (obj instanceof Dog)
                dogs.add((Dog) obj);
            else if (obj instanceof Cat)
                cats.add((Cat) obj);
        } else if (obj instanceof Product)
            products.add((Product) obj);
        else if (obj instanceof Cage)
            cages.add((Cage) obj);
        else if (obj instanceof Plant)
            plants.add((Plant) obj);
    }

    public ArrayList<Storable> getAndDiscardProductsAndCagedAnimals(int row, int column) {
        Cell cell = cells.get(row).get(column);

        ArrayList<Product> products = cell.getProducts();
        ArrayList<Cage> cages = cell.getCages();

        this.products.removeAll(products);
        this.cages.removeAll(cages);
        for (Cage cage : cages)
            try {
                cell.discardFromCell(cage);
            } catch (NotFoundException e) {
                e.printStackTrace();        //rokh nemide
            }
        for (Product product : products)
            try {
                cell.discardFromCell(product);
            } catch (NotFoundException e) {
                e.printStackTrace();        //nabayad rokh bede
            }

        ArrayList<Storable> output = new ArrayList<>(products);

        for (Cage cage : cages) {
            Predator predator = cage.getCagedPredator();
            output.add(predator);
        }
        return output;
    }

    public void discardAnimal(Animal animal) throws NotFoundException {
        if (!allItemsInMap.contains(animal))
            throw new NotFoundException();

        int row = animal.getPosition().getRow();
        int column = animal.getPosition().getColumn();
        Cell cell = cells.get(row).get(column);

        allItemsInMap.remove(animal);
        animals.remove(animal);
        cell.discardFromCell(animal);

        if (animal instanceof Predator)
            predators.remove(animal);
        else if (animal instanceof Domestic) {
            domestics.remove(animal);
            mission.getLevelRequirementsChecker().domesticIsDiscardedFromMap((Domestic) animal);
        } else if (animal instanceof Dog)
            dogs.remove(animal);
        else if (animal instanceof Cat)
            cats.remove(animal);

//        if (animal instanceof Dog)
//            System.out.println("Dog Discarded");
    }

    public void updateAnimalPosition(Animal animal, int previousRow, int previousColumn, int nextRow, int nextColumn)
            throws NotFoundException {

        Cell previousCell = cells.get(previousRow).get(previousColumn);
        previousCell.discardFromCell(animal);
        Cell nextCell = cells.get(nextRow).get(nextColumn);
        nextCell.addToCell(animal);
    }

    public boolean isPlanted(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Plant plant = cells.get(row).get(column).getPlant();
        return plant != null;
    }

    public boolean isPlanted(int row, int column) {
        Plant plant = cells.get(row).get(column).getPlant();
        return plant != null;
    }

    public void removePlant(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Cell cell = cells.get(row).get(column);
        Plant plant = cell.getPlant();
        allItemsInMap.remove(plant);
        plants.remove(plant);
        cell.discardPlant();
    }

    public ArrayList<Predator> getAllPredatorsInMap() {
        return predators;
    }

    public ArrayList<Animal> getAllAnimalsInMap() {
        return animals;
    }

    public ArrayList<Domestic> getAllDomesticsInMap() {
        return domestics;
    }

    public Product getClosestProduct(Position position) {
        double minDistance = MAP_SIZE * MAP_SIZE;
        Product closestProduct = null;
        for (Product product : products) {
            double distance = Utils.calculateDistance(product.getPosition(), position);
            if (distance < minDistance) {
                minDistance = distance;
                closestProduct = product;
            }
        }
        return closestProduct;
    }

    public Plant getClosestPlant(Position position) {
        Plant closestPlant = null;
        double minDistance = MAP_SIZE * MAP_SIZE;       //haminjuri ye adade kheili gonde
        for (Plant plant : plants) {
            double distance = Utils.calculateDistance(position, plant.getPosition());
            if (distance < minDistance) {
                closestPlant = plant;
                minDistance = distance;
            }
        }
        return closestPlant;
    }

    public Predator getClosestPredator(Position position) {
        Predator closestPredator = null;
        double minDistance = MAP_SIZE * MAP_SIZE;       //haminjuri ye adade kheili gonde
        for (Predator predator : predators) {
            double distance = Utils.calculateDistance(position, predator.getPosition());
            if (distance < minDistance) {
                closestPredator = predator;
                minDistance = distance;
            }
        }
        return closestPredator;
    }

    public ArrayList<Product> getAndDiscardProductsInCell(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Cell cell = cells.get(row).get(column);
        ArrayList<Product> discardedProducts = cell.getProducts();
        for (Product product : discardedProducts) {
            try {
                cell.discardFromCell(product);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        allItemsInMap.removeAll(discardedProducts);
        this.products.removeAll(discardedProducts);
        return discardedProducts;
    }

    public ArrayList<Domestic> getDomesticsInCell(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Cell cell = cells.get(row).get(column);
        return cell.getDomestics();
    }

    public ArrayList<Predator> getPredatorsInCell(int row, int column) {
        Cell cell = cells.get(row).get(column);
        return cell.getPredators();
    }

    public ArrayList<Predator> getPredatorsInCell(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Cell cell = cells.get(row).get(column);
        return cell.getPredators();
    }

    public ArrayList<Cat> getCats() {
        return cats;
    }

    public ArrayList<Dog> getDogs() {
        return dogs;
    }

    public void print() {
        int thisrow = 0;
        for (ArrayList<Cell> row : cells) {
            System.out.print(thisrow + ":");
            for (Cell cell : row) {
                System.out.print("{");
                cell.print();
                System.out.print("}");
            }
            System.out.println();
            thisrow++;
        }
    }
}
