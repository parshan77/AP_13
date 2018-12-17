package Model;

import Exceptions.*;
import Interfaces.VisibleInMap;
import Model.Animals.Animal;
import Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;
    public static int MAP_SIZE = 1024;

    public Map() {
        cells = new ArrayList<>();
        for (int i = 0; i < MAP_SIZE; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < MAP_SIZE; j++)
                cells.get(i).add(new Cell());
        }
    }

    public Plant getClosestPlant(Position position) throws MapGettingClosestPlantException {
        Plant closestPlant = null;
        double minDistance = MAP_SIZE * MAP_SIZE;       //haminjuri ye adade kheili gonde
        for (ArrayList<Cell> cellsRows : cells) {
            for (Cell cell : cellsRows) {
                Plant plant;
                try {
                    plant = cell.getPlant();
                    double distance = Utils.calculateDistance(position, plant.getPosition());
                    if (distance < minDistance) {
                        closestPlant = plant;
                        minDistance = distance;
                    }
                } catch (CellNoPlantExistsException e) {}
            }
        }
        if (closestPlant == null) throw new MapGettingClosestPlantException();
        return closestPlant;
    }

    public Cell getCell(int row, int column) throws MapCellGettingException {
        try {
            return cells.get(row).get(column);
        } catch (Exception e) {
            throw new MapCellGettingException();
        }
    }

    public void updateAnimalPosition(Animal animal, Position previousPosition, Position nextPosition)
            throws MapAnimalPositionUpdatingException {
        int previousRow = previousPosition.getRow();
        int previousColumn = previousPosition.getColumn();
        int nextRow = nextPosition.getRow();
        int nextColumn = nextPosition.getColumn();
        try {
            cells.get(previousRow).get(previousColumn).discardAnimal(animal);
        } catch (CellAnimalDiscardingException e) {
            throw new MapAnimalPositionUpdatingException();
        }
        cells.get(nextRow).get(nextColumn).addToCell(animal);
    }

    public void addToMap(VisibleInMap object) {
        Position objectPosition = object.getPosition();
        int row = objectPosition.getRow();
        int column = objectPosition.getColumn();
        cells.get(row).get(column).addToCell(object);
    }
}
