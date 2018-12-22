package Model;

import Exceptions.NotValidCoordinatesException;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) throws NotValidCoordinatesException {
        if ((row < 0) || (row >= Map.MAP_SIZE) || (column < 0) || (column >= Map.MAP_SIZE))
            throw new NotValidCoordinatesException();
        this.row = row;
        this.column = column;
    }

    public void changePosition(Direction direction) throws NotValidCoordinatesException {
        if ((this.row + direction.getRowDirection() < 0)||
                (this.row + direction.getRowDirection() >= Map.MAP_SIZE)||
                (this.column + direction.getColumnDirection() < 0)||
                (this.column + direction.getColumnDirection() >= Map.MAP_SIZE))
            throw new NotValidCoordinatesException();
        this.row += direction.getRowDirection();
        this.column += direction.getColumnDirection();
    }

    public void setPosition(int row, int column) throws NotValidCoordinatesException {
        if ((row < 0) || (row >= Map.MAP_SIZE) || (column < 0) || (column >= Map.MAP_SIZE))
            throw new NotValidCoordinatesException();
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) throws NotValidCoordinatesException {
        if ((row < 0)||(row >= Map.MAP_SIZE)) throw new NotValidCoordinatesException();
        this.row = row;
    }

    public void setColumn(int column) throws NotValidCoordinatesException {
        if ((column < 0) || (column >= Map.MAP_SIZE)) throw new NotValidCoordinatesException();
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Position)) return false;
        Position position = (Position) obj;
        return (position.getColumn() == this.column) && (position.getRow() == this.row);
    }

}
