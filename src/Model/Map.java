package Model;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> cells;

    public Map() {
        cells = new ArrayList<ArrayList<Cell>>();
        for (int i = 0; i < 10; i++) {
            cells.add( new ArrayList<Cell>());
        }
    }
}
