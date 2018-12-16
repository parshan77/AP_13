package Model.Animals;

import Interfaces.Movable;
import Interfaces.VisibleInMap;
import Model.Direction;
import Model.Position;
import Exceptions.DirectionInitializingException;
import Exceptions.DirectionNotPossibleSettingException;
import Exceptions.PositionNotPossibleSettingxception;
import Model.Direction;
import Model.Map;


public abstract class Animal implements Movable, VisibleInMap {
    private int pace;
    protected Position position;
    protected Direction direction;

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void move() {
        for (int i = 0; i < pace; i++) {
            step();
        }
    }

    private void step() {
        try {
            position.changePosition(direction);
        } catch (PositionNotPossibleSettingxception e) {
            if (position.getRow() == 0) {
                try {
                    direction.setDirection(1, direction.getColumnDirection());
                } catch (DirectionNotPossibleSettingException e1) {}
            }

            if (position.getColumn() == 0) {
                try {
                    direction.setDirection(direction.getRowDirection(), 1);
                } catch (DirectionNotPossibleSettingException e4) {}
            }

            if (position.getRow() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(-1, direction.getColumnDirection());
                } catch (DirectionNotPossibleSettingException e6) {}
            }

            if (position.getColumn() == Map.MAP_SIZE - 1) {
                try {
                    direction.setDirection(direction.getRowDirection(), -1);
                } catch (DirectionNotPossibleSettingException e8) {}
            }

        }
    }



}
