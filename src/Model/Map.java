package Model;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Model.Products.Product;
import Utils.Utils;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;
    public static int MAP_SIZE = 1024;

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

    public ArrayList<Product> pickUpProduct(int row, int column) {
        return cells.get(row).get(column).collectProducts();
    }

    public void plant(int row, int column) {
        int minRow = Math.max(0, row - 1);
        int minColumn = Math.max(0, column - 1);
        int maxRow = Math.min(MAP_SIZE - 1, row + 1);
        int maxColumn = Math.max(MAP_SIZE - 1, column + 1);
        for (int i = minRow; i <= maxRow; i++) {
            for (int j = minColumn; j <= maxColumn; j++) {
                cells.get(i).get(j).plantHere();
            }
        }
    }

    public Plant getClosestPlant(Position position) throws NotFoundException {
        Plant closestPlant = null;
        double minDistance = MAP_SIZE * MAP_SIZE;       //haminjuri ye adade kheili gonde
        for (ArrayList<Cell> cellsRows : cells) {
            for (Cell cell : cellsRows) {
                Plant plant = cell.getPlant();
                if (plant != null) {
                    double distance = Utils.calculateDistance(position, plant.getPosition());
                    if (distance < minDistance) {
                        closestPlant = plant;
                        minDistance = distance;
                    }
                }
            }
        }
        if (closestPlant == null) return null;
        return closestPlant;
    }

    public Cell getCell(int row, int column) throws NotValidCoordinatesException {
        try {
            return cells.get(row).get(column);
        } catch (Exception e) {
            throw new NotValidCoordinatesException();
        }
    }

    public void updateAnimalPosition(Animal animal, Position previousPosition, Position nextPosition)
            throws NotFoundException {
        int previousRow = previousPosition.getRow();
        int previousColumn = previousPosition.getColumn();
        int nextRow = nextPosition.getRow();
        int nextColumn = nextPosition.getColumn();
        cells.get(previousRow).get(previousColumn).discardAnimal(animal);
        //age in annimal tu un cell mojud nabashe tu khatte ghabl exception rokh mide o kharej mishim
        cells.get(nextRow).get(nextColumn).addToCell(animal);
    }

    public void addToMap(VisibleInMap object) {
        Position objectPosition = object.getPosition();
        int row = objectPosition.getRow();
        int column = objectPosition.getColumn();
        cells.get(row).get(column).addToCell(object);
    }

}
