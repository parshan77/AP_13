package Model;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;

    public Map() {
        cells = new ArrayList<>();
        for (int i = 0; i < 1024; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < 1024; j++)
                cells.get(i).add(new Cell());
        }
    }

}
