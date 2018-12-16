package Model;

import Exceptions.CellNoPredatorFoundException;
import Exceptions.MapCellGettingException;
import Exceptions.PositionInitializingException;
import Interfaces.VisibleInMap;
import Model.Animals.Predator;

public class Cage implements VisibleInMap {
    private Predator predator;
    private Position position;
    private Map map;

    public Cage(Map map, int row, int column)
            throws PositionInitializingException, MapCellGettingException, CellNoPredatorFoundException {
        this.map = map;
        position = new Position(row, column);
        predator = map.getCell(row, column).getPredator();
        map.addToMap(this);
    }

    @Override
    public void show() {
        //todo
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
