package Model.Placement;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Model.Animals.Domestic;
import Model.Animals.Predator;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Cage;
import Model.Plant;
import Model.Products.Product;
import Utils.Utils;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;
    public static int MAP_SIZE = 1024;
    private ArrayList<VisibleInMap> allItemsInMap = new ArrayList<>();

    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Predator> predators = new ArrayList<>();
    private ArrayList<Domestic> domestics = new ArrayList<>();
    private ArrayList<Cat> cats = new ArrayList<>();
    private ArrayList<Dog> dogs = new ArrayList<>();

    private ArrayList<Plant> plants = new ArrayList<>();
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Cage> cages = new ArrayList<>();

    public Map() {
        cells = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < MAP_SIZE; j++) {
                try {
                    cells.get(i).add(new Cell(new Position(i, j)));
                } catch (NotValidCoordinatesException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addToMap(VisibleInMap obj) {
        int row = obj.getPosition().getRow();
        int column = obj.getPosition().getColumn();
        Cell cell = cells.get(row).get(column);

        cell.addItemToCell(obj);
        allItemsInMap.add(obj);

        if (obj instanceof Animal) {
            animals.add((Animal) obj);

            if (obj instanceof Predator) {
                Predator predator = (Predator) obj;
                predators.add(predator);
            } else if (obj instanceof Domestic) {
                Domestic domestic = (Domestic) obj;
                domestics.add(domestic);
            }
            if (obj instanceof Dog) {
                Dog dog = (Dog) obj;
                dogs.add(dog);
            } else if (obj instanceof Cat) {
                Cat cat = (Cat) obj;
                cats.add(cat);
            }
        } else if (obj instanceof Product) {
            Product product = (Product) obj;
            products.add(product);
        } else if (obj instanceof Cage) {
            Cage cage = (Cage) obj;
            cages.add(cage);
        }
        //todo:plant nabayad injuri add beshe
    }

    public ArrayList<Product> getProductsInCell(int row, int column) {
        Cell cell = cells.get(row).get(column);
        return cell.getProducts();
    }

    public ArrayList<Product> getProductsInCell(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Cell cell = cells.get(row).get(column);
        return cell.getProducts();
    }

    public ArrayList<Domestic> getDomesticsInCell(int row, int column) {
        Cell cell = cells.get(row).get(column);
        return cell.getDoemstics();
    }

    public ArrayList<Domestic> getDomesticsInCell(Position position) {
        int row = position.getRow();
        int column = position.getColumn();
        Cell cell = cells.get(row).get(column);
        return cell.getDoemstics();
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

    public ArrayList<Domestic> getAllDomesticsInMap(Position position) {
        return domestics;
    }

    public void discardAnimals(ArrayList<Animal> animals) {
        for (Animal animal : animals) {
            try {
                discardAnimal(animal);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Animal> getAllAnimalsInMap() {
        return animals;
    }

    public ArrayList<Storable> pickUpProductsAndCagedAnimals(int row, int column) {
        Cell cell = cells.get(row).get(column);

        ArrayList<Product> products = cell.getProducts();
        ArrayList<Cage> cages = cell.getCages();

        this.products.removeAll(products);
        cell.discardProducts(products);
        this.cages.removeAll(cages);
        cell.discardCages(cages);


        ArrayList<Storable> output = new ArrayList<>();
        output.addAll(products);        // TODO: 12/26/2018 intellij chi mige?

        for (Cage cage : cages) {
            Predator predator = cage.getCagedPredator();
            output.add((Storable)predator);
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
        cell.discardAnimal(animal);

        if (animal instanceof Predator) {
            Predator predator = (Predator) animal;
            predators.remove(predator);
        } else if (animal instanceof Domestic) {
            Domestic domestic = (Domestic) animal;
            domestics.remove(domestic);
        }
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dogs.remove(dog);
        } else if (animal instanceof Cat) {
            Cat cat = (Cat) animal;
            cats.remove(cat);
        }
    }

    //age plant nadashtim in null mide
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

    public void updateAnimalPosition(Animal animal, int previousRow, int previousColumn, int nextRow, int nextColumn)
            throws NotFoundException {

        Cell previousCell = cells.get(previousRow).get(previousColumn);
        previousCell.discardAnimal(animal);
        Cell nextCell = cells.get(nextRow).get(nextColumn);
        nextCell.addItemToCell(animal);
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
        cells.get(row).get(column).discardPlant();
    }

    public void plantInCell(Plant plant) throws PlantingFailureException {
        int row = plant.getPosition().getRow();
        int column = plant.getPosition().getColumn();
        if (!cells.get(row).get(column).addPlant(plant)) {
            throw new PlantingFailureException();
        }
    }
}
