package Model.Animals;

import Exceptions.NotFoundException;
import Exceptions.NotValidCoordinatesException;
import Exceptions.PlantingFailureException;
import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Screen.Map;


public abstract class Animal implements Movable, VisibleInMap {
    public int pace;
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
            Position previousPosition = position;
            position.changePosition(direction);
            map.updateAnimalPosition(this, previousPosition.getRow(), previousPosition.getColumn(),
                    position.getRow(), position.getColumn());

            //todo: doroste?
        } catch (NotValidCoordinatesException e) {
            if (position.getRow() == 0) {
                direction.setDirection(1, direction.getColumnDirection());
            }

            if (position.getColumn() == 0) {
                direction.setDirection(direction.getRowDirection(), 1);
            }

            if (position.getRow() == Map.MAP_SIZE - 1) {
                direction.setDirection(-1, direction.getColumnDirection());
            }

            if (position.getColumn() == Map.MAP_SIZE - 1) {
                direction.setDirection(direction.getRowDirection(), -1);
            }
            Position previousPosition = position;
            position.changePosition(direction);
            try {
                map.updateAnimalPosition(this, previousPosition.getRow(), previousPosition.getColumn(),
                        position.getRow(), position.getColumn());
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
