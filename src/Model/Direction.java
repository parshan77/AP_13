package Model;

import Exceptions.DirectionInitializingException;
import Exceptions.DirectionNotPossibleSettingException;

public class Direction {
    private int rowDirection;
    private int columnDirection;


    public Direction(int rowDirection, int columnDirection) throws DirectionInitializingException {
        if ((rowDirection < -1) || (rowDirection > 1) || (columnDirection < -1) || (columnDirection > 1))
            throw new DirectionInitializingException();
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public void setColumnDirection(int columnDirection) throws DirectionNotPossibleSettingException {
        if ((columnDirection < 0)||(columnDirection >=Map.MAP_SIZE))
            throw new DirectionNotPossibleSettingException();
        this.columnDirection = columnDirection;
    }

    public void setRowDirection(int rowDirection) throws DirectionNotPossibleSettingException {
        if ((rowDirection <0)||(rowDirection >= Map.MAP_SIZE))
            throw new DirectionNotPossibleSettingException();
        this.rowDirection = rowDirection;
    }

    public void setDirection(int rowDirection, int columnDirection) throws DirectionNotPossibleSettingException {
        if ((rowDirection < -1) || (rowDirection > 1) || (columnDirection < -1) || (columnDirection > 1))
            throw new DirectionNotPossibleSettingException();
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColumnDirection() {
        return columnDirection;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Direction)) return false;
        Direction direction = (Direction) obj;
        return (direction.getColumnDirection() == this.columnDirection) &&
                (direction.getRowDirection() == this.rowDirection);
    }
}
