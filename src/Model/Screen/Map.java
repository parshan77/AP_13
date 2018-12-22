package Model.Screen;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Model.Animals.Predator;
import Model.Animals.Prey;
import Model.Animals.Seekers.Cat;
import Model.Animals.Seekers.Dog;
import Model.Cage;
import Model.Plant;
import Model.Position;
import Model.Products.Product;
import Utils.Utils;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;
    public static int MAP_SIZE = 1024;
    private ArrayList<VisibleInMap> allItemsInMap = new ArrayList<>();

    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Predator> predators = new ArrayList<>();
    private ArrayList<Prey> preys = new ArrayList<>();
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

    public void addToMap(VisibleInMap obj) throws PlantingFailureException {
        allItemsInMap.add(obj);
        int row = obj.getPosition().getRow();
        int column = obj.getPosition().getColumn();
        Cell cell = cells.get(row).get(column);
        cell.addToCellAllItems(obj);

        if (obj instanceof Animal) {
            animals.add((Animal) obj);
            cell.addToAnimals((Animal) obj);

            if (obj instanceof Predator) {
                Predator predator = (Predator) obj;
                predators.add(predator);
                cell.addToPredators(predator);
            } else if (obj instanceof Prey) {
                Prey prey = (Prey) obj;
                preys.add(prey);
                cell.addToPreys(prey);
            }
            if (obj instanceof Dog) {
                Dog dog = (Dog) obj;
                dogs.add(dog);
                cell.addToDogs(dog);
            } else if (obj instanceof Cat) {
                Cat cat = (Cat) obj;
                cats.add(cat);
                cell.addToCats(cat);
            }
        } else if (obj instanceof Product) {
            Product product = (Product) obj;
            products.add(product);
            cell.addToProducts(product);
        } else if (obj instanceof Plant) {
            Plant plant = (Plant) obj;
            cell.addPlant(plant);
            //agar tuye un khune az ghabl plant bashe be in khatt nemiresim
            plants.add(plant);
        } else if (obj instanceof Cage) {
            Cage cage = (Cage) obj;
            cages.add(cage);
            cell.addToCages(cage);
        }
    }

    public ArrayList<Product> pickUpProduct(int row, int column) {
        Cell cell = cells.get(row).get(column);
        ArrayList<Product> products = cell.getProducts();
        cell.clearProductsList();
        this.products.removeAll(products);
        return products;
    }

    public void discardAnimal(Animal animal) throws NotFoundException {
        if (!allItemsInMap.contains(animal))
            throw new NotFoundException();

        int row = animal.getPosition().getRow();
        int column = animal.getPosition().getColumn();
        Cell cell = cells.get(row).get(column);

        allItemsInMap.remove(animal);
        animals.remove(animal);
        if (!cell.getAnimals().remove(animal)) { //todo:remove inja khodesh pak mikone dg
            System.out.println("inja ridi");//todo:inaro vase hame check kon
        }

        if (animal instanceof Predator) {
            Predator predator = (Predator) animal;
            this.predators.remove(predator);
            cell.getPredators().remove(predator);
        } else if (animal instanceof Prey) {
            Prey prey = (Prey) animal;
            preys.remove(prey);
            cell.getPreys().remove(prey);
        }
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dogs.remove(dog);
            cell.getDogs().remove(dog);
        } else if (animal instanceof Cat) {
            Cat cat = (Cat) animal;
            cats.remove(cat);
            cell.getCats().remove(cat);
        }
    }

    public void plantInCell(int row, int column) throws PlantingFailureException {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.max(MAP_SIZE - 1, column + 1);

        int numberOfPlantsPlanted = 0;
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minColumn; j <= maxColumn; j++) {
                try {
                    cells.get(i).get(j).addPlant(new Plant(new Position(i,j)));
                    numberOfPlantsPlanted++;
                } catch (PlantingFailureException e) {
                } catch (NotValidCoordinatesException e) {
                    e.printStackTrace();
                }
            }
        }
        if (numberOfPlantsPlanted == 0) {
            throw new PlantingFailureException();
        }
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

    public void updateAnimalPosition(Animal animal, Position previousPosition, Position nextPosition)
            throws NotFoundException {

        int previousRow = previousPosition.getRow();
        int previousColumn = previousPosition.getColumn();
        int nextRow = nextPosition.getRow();
        int nextColumn = nextPosition.getColumn();

        Cell previousCell = cells.get(previousRow).get(previousColumn);
        previousCell.discardAnimal(animal);
        Cell nextCell = cells.get(nextRow).get(nextColumn);
        nextCell.addToAnimals(animal);
    }

}
