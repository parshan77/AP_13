package Model.Animals;

import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;
import Utils.Utils;


public abstract class Animal implements Movable, VisibleInMap {
    protected String name;
    protected int pace;
    protected Position position;
    protected Direction direction;
    public Map map;

    Animal(Map map, Direction direction, Position position) {
        this.position = position;
        this.direction = direction;
        this.map = map;
    }

    protected void step() {
        try {
            int previousRow = position.getRow();
            int previousColumn = position.getColumn();
            position.changePosition(direction);
            map.updateAnimalPosition(this, previousRow, previousColumn, position.getRow(), position.getColumn());
        } catch (NotValidCoordinatesException e) {
            if (position.getRow() == 0)
                direction.setRowDirection(1);

            if (position.getColumn() == 0)
                direction.setColumnDirection(1);

            if (position.getRow() == Map.MAP_SIZE - 1)
                direction.setRowDirection(-1);

            if (position.getColumn() == Map.MAP_SIZE - 1)
                direction.setColumnDirection(-1);

            int previousRow = position.getRow();
            int previousColumn = position.getColumn();
            position.changePosition(direction);
            try {
                map.updateAnimalPosition(this, previousRow, previousColumn, position.getRow(), position.getColumn());
            } catch (NotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            // -> updateAnimalPosition ino mide -> rokh nemide
        }
    }

    protected boolean smartStep(Position targetPosition) {
        int targetRow = targetPosition.getRow();
        int targetColumn = targetPosition.getColumn();

        if (position.equals(targetPosition)) {
            direction = Utils.getRandomDirection();
            return true;
        }

        if (position.getRow() < targetRow)
            direction.setRowDirection(1);
        else if (position.getRow() > targetRow)
            direction.setRowDirection(-1);
        else direction.setRowDirection(0);

        if (position.getColumn() < targetColumn)
            direction.setColumnDirection(1);
        else if (position.getColumn() > targetColumn)
            direction.setColumnDirection(-1);
        else direction.setColumnDirection(0);

        int previousRow = position.getRow();
        int previousColumn = position.getColumn();
        position.changePosition(direction);     //exception nemide

        try {
            map.updateAnimalPosition(this, previousRow, previousColumn, position.getRow(), position.getColumn());
        } catch (NotFoundException e) {
            e.printStackTrace();
            //rokh nemide
        }

        if (position.equals(targetPosition)) {
            direction = Utils.getRandomDirection();
            return true;
        }
        return false;
    }

    @Override
    public void move() {
        for (int i = 0; i < pace; i++)
            step();
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public String getName() {
        return name;
    }
}
