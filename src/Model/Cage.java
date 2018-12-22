package Model;

import Exceptions.CellExceptions.CellNoPredatorFoundException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.VisibleInMap;
import Model.Animals.Predator;

public class Cage implements VisibleInMap {
    private Position position;
    private Mission mission;
    private Predator cagedPredator;

    public Cage(Mission mission, Position position) {
        this.position = position;
        this.mission = mission;
    }

    private void cagePredator(Predator predator) {
        this.cagedPredator= predator;
    }

    public Predator getCagedPredator() {
        return cagedPredator;
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
