package Model;

import Exceptions.PositionInitializingException;
import Exceptions.PositionNotPossibleSettingxception;

public class Position {
    private int row;
    private int column;

    public Position(int row, int column) throws PositionInitializingException {
        if ((row < 0) || (row >= Map.MAP_SIZE) || (column < 0) || (column >= Map.MAP_SIZE))
            throw new PositionInitializingException();
        this.row = row;
        this.column = column;
    }

    public void setPosition(int row, int column) throws PositionNotPossibleSettingxception {
        if ((row < 0) || (row >= Map.MAP_SIZE) || (column < 0) || (column >= Map.MAP_SIZE))
            throw new PositionNotPossibleSettingxception();
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) return false;
        Position position = (Position) obj;
        return (position.getColumn() == this.column) && (position.getRow() == this.row);
    }

}
