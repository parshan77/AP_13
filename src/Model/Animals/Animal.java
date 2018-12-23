package Model.Animals;

import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Direction;
import Model.Map;
import Model.Position;


public abstract class Animal implements Movable, VisibleInMap {
    public int pace = 3;
    protected Position position;
    protected Direction direction;
    public Map map;

    public Animal(Map map, Direction direction, Position position) {
        this.position = position;
        this.direction = direction;
        this.map = map;
    }

    @Override
    public Position getPosition() {
        return position;
    }


    public void step() {
        try {
            map.getCell(position.getRow(), position.getColumn()).discardAnimal(this);
            position.changePosition(direction);
            map.getCell(position.getRow(), position.getColumn()).addToCell(this);
            //todo: doroste?
        } catch (NotValidCoordinatesException e) {
            if (position.getRow() == 0) {
                try {
                    direction.setDirection(1, direction.getColumnDirection());
                } catch (NotValidCoordinatesException e1) {
                    e1.printStackTrace();
                }
            }

            if (position.getColumn() == 0) {
                try {
                    direction.setDirection(direction.getRowDirection(), 1);
                } catch (NotValidCoordinatesException e4) {e4.printStackTrace();}
            }

            if (position.getRow() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(-1, direction.getColumnDirection());
                } catch (NotValidCoordinatesException e6) {e6.printStackTrace();}
            }

            if (position.getColumn() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(direction.getRowDirection(), -1);
                } catch (NotValidCoordinatesException e8) {e8.printStackTrace();}
            }
            try {
                map.getCell(position.getRow(), position.getColumn()).discardAnimal(this);
                position.changePosition(direction);
                map.getCell(position.getRow(), position.getColumn()).addToCell(this);
            } catch (NotValidCoordinatesException e1) {
                e1.printStackTrace();
            } catch (NotFoundException e1) {
                e1.printStackTrace();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move() {
        for (int i = 0; i < pace; i++) {
            step();
        }
    }
}
