package Model.Animals;

import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Position;

public abstract class Animal implements Movable, VisibleInMap {
    private int pace;
    private Position position;

    @Override
    public Position getPosition() {
        return position;
    }
}
