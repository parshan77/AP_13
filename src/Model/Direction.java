package Model;

import Exceptions.NotValidCoordinatesException;
import Model.Screen.Map;

public class Direction {
    private int rowDirection;
    private int columnDirection;


    public Direction(int rowDirection, int columnDirection) throws NotValidCoordinatesException {
        if ((rowDirection < -1) || (rowDirection > 1) || (columnDirection < -1) || (columnDirection > 1))
            throw new NotValidCoordinatesException();
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public void setColumnDirection(int columnDirection) throws NotValidCoordinatesException {
        if ((columnDirection < 0)||(columnDirection >= Map.MAP_SIZE))
            throw new NotValidCoordinatesException();
        this.columnDirection = columnDirection;
    }

    public void setRowDirection(int rowDirection) throws NotValidCoordinatesException{
        if ((rowDirection <0)||(rowDirection >= Map.MAP_SIZE))
            throw new NotValidCoordinatesException();
        this.rowDirection = rowDirection;
    }

    public void setDirection(int rowDirection, int columnDirection) throws NotValidCoordinatesException {
        if ((rowDirection < -1) || (rowDirection > 1) || (columnDirection < -1) || (columnDirection > 1))
            throw new NotValidCoordinatesException();
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
        if (obj == null) return false;
        if (!(obj instanceof Direction)) return false;
        Direction direction = (Direction) obj;
        return (direction.getColumnDirection() == this.columnDirection) &&
                (direction.getRowDirection() == this.rowDirection);
    }
}
