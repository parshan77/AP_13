package Model;

import Exceptions.CellNotExistsException;
import Exceptions.NoPlantFoundException;
import Interfaces.VisibleInMap;
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

    public Position getClosestPlant(Position position) throws NoPlantFoundException {
        HashMap<Plant, Double> plantToDistanceHashMap = new HashMap<>();
        //todo: hashcode ro lazeme implement konim?
        for (ArrayList<Cell> cellsRows : cells) {
            for (Cell cell : cellsRows) {
                Plant plant = cell.getPlant();
                if (plant != null) {
                    double distance = Utils.calculateDistance(position, plant.getPosition());
                    plantToDistanceHashMap.put(plant, distance);
                }
            }
        }
        if (plantToDistanceHashMap.isEmpty())
            throw new NoPlantFoundException();
        double minDistance = -1;
        Plant closestPlant = null ;
        for (Plant plant : plantToDistanceHashMap.keySet()) {
            if (minDistance == -1) {
                minDistance = Utils.calculateDistance(plant.getPosition(), position);
                closestPlant = plant;
            } else {
                if (plantToDistanceHashMap.get(plant) < minDistance)
                    closestPlant = plant;
            }
        }
        return closestPlant.getPosition();
    }

    public Cell getCell(int row, int column) throws CellNotExistsException {
        try {
            return cells.get(row).get(column);
        } catch (Exception e) {
            throw new CellNotExistsException();
        }
    }

    public void addToMap(VisibleInMap object) {
        Position objectPosition = object.getPosition();
        int row = objectPosition.getRow();
        int column = objectPosition.getColumn();
        cells.get(row).get(column).addToCell(object);
    }
}
