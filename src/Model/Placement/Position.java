package Model.Placement;

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
        int addedRow = direction.getRowDirection();
        int addedColumn = direction.getColumnDirection();
        if ((this.row + addedRow < 0) || (this.row + addedRow >= Map.MAP_SIZE) ||
                (this.column + addedColumn < 0) || (this.column + addedColumn >= Map.MAP_SIZE))
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
        if ((row < 0) || (row >= Map.MAP_SIZE)) throw new NotValidCoordinatesException();
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
