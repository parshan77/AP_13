package Model.Placement;

import Exceptions.NotValidCoordinatesException;

public class Direction {
    private int rowDirection;
    private int columnDirection;

    public Direction(int rowDirection, int columnDirection) throws NotValidCoordinatesException {
        if ((rowDirection < -1) || (rowDirection > 1) || (columnDirection < -1) || (columnDirection > 1))
            throw new NotValidCoordinatesException();
        if ((rowDirection == 0) && (columnDirection == 0))
            throw new NotValidCoordinatesException();
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public Direction() {

    }

    public String getName() {
        if ((rowDirection == -1) && (columnDirection == 0))
            return "up";
        else if ((rowDirection == -1) && (columnDirection == 1))
            return "up_right";
        else if ((rowDirection == 0) && (columnDirection == 1))
            return "right";
        else if ((rowDirection == 1) && (columnDirection == 1))
            return "down_right";
        else if ((rowDirection == 1) && (columnDirection == 0))
            return "down";
        else if ((rowDirection == 1) && (columnDirection == -1))
            return "down_left";
        else if ((rowDirection == 0) && (columnDirection == -1))
            return "left";
        else if ((rowDirection == -1) && (columnDirection == -1))
            return "up_left";
        return null;
    }

    public void setColumnDirection(int columnDirection) throws NotValidCoordinatesException {
        if ((columnDirection < -1) || (columnDirection > 1))
            throw new NotValidCoordinatesException();
        if ((rowDirection == 0) && (columnDirection == 0))
            throw new NotValidCoordinatesException();
        this.columnDirection = columnDirection;
    }

    public void setRowDirection(int rowDirection) throws NotValidCoordinatesException {
        if ((rowDirection < -1) || (rowDirection > 1))
            throw new NotValidCoordinatesException();
        if ((rowDirection == 0) && (columnDirection == 0))
            throw new NotValidCoordinatesException();
        this.rowDirection = rowDirection;
    }

    public void setDirection(int rowDirection, int columnDirection) throws NotValidCoordinatesException {
        if ((rowDirection < -1) || (rowDirection > 1) || (columnDirection < -1) || (columnDirection > 1))
            throw new NotValidCoordinatesException();
        if ((rowDirection == 0) && (columnDirection == 0))
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
