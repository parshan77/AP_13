package Model.Animals;

import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Placement.Direction;
import Model.Placement.Map;
import Model.Placement.Position;
import Utils.Utils;
import View.AnimalViewer;


public abstract class Animal implements Movable, VisibleInMap {
    protected String name;
    protected int pace;
    protected Position position;
    protected Direction direction;
    protected Map map;

    private AnimalViewer animalViewer;

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
            animalViewer.playMoveAnimation( previousRow, previousColumn, position.getRow(), position.getColumn());
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
            animalViewer.playMoveAnimation( previousRow, previousColumn,
                    previousRow + direction.getRowDirection(),
                    previousColumn + direction.getColumnDirection());
            try {
                map.updateAnimalPosition(this, previousRow, previousColumn, position.getRow(), position.getColumn());
            } catch (NotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    protected boolean smartStep(Position targetPosition) {
        if (position.equals(targetPosition)) {
            direction = Utils.getRandomDirection();
            return true;
        }

        int targetRow = targetPosition.getRow();
        int targetColumn = targetPosition.getColumn();

        int rowDirection = Integer.compare(targetRow, position.getRow());
        int columnDirection = Integer.compare(targetColumn, position.getColumn());
        direction.setDirection(rowDirection, columnDirection);

        int previousRow = position.getRow();
        int previousColumn = position.getColumn();
        position.changePosition(direction);     //exception nemide
        animalViewer.playMoveAnimation( previousRow,previousColumn,
                previousRow + direction.getRowDirection(),
                previousColumn + direction.getColumnDirection());
        try {
            map.updateAnimalPosition(this, previousRow, previousColumn, position.getRow(), position.getColumn());
            animalViewer.playMoveAnimation(previousRow, previousColumn,
                    previousRow + direction.getRowDirection(),
                    previousColumn + direction.getColumnDirection());
        } catch (NotFoundException e) {
            e.printStackTrace();        //rokh nemide
        }

        if (position.equals(targetPosition)) {
            direction = Utils.getRandomDirection();
            return true;
        }
        return false;
    }

    public int getPace() {
        return pace;
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

    public void setAnimalViewer(AnimalViewer animalViewer) {
        this.animalViewer = animalViewer;
    }
}
