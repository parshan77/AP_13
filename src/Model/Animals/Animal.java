package Model.Animals;

import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Direction;
import Model.Position;


public abstract class Animal implements Movable, VisibleInMap {
    protected int pace;
    protected Position position;
    protected Direction direction;

    @Override
    public Position getPosition() {
        return position;
    }





}
