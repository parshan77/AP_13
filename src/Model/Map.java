package Model;

import Exceptions.CellNotExistsException;

import java.util.ArrayList;

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

    public Cell getCell(int x, int y) throws CellNotExistsException {
        try {
            return cells.get(x).get(y);
        } catch (Exception e) {
            throw new CellNotExistsException();
        }
    }

}
