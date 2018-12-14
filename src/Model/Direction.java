package Model;

import Exceptions.NotPossibleDirectionSettingException;
import Exceptions.NotPossiblePositionSettingException;

public class Direction {
    private int rowDirection;
    private int columnDirection;

    public void setDirection(int rowDirection, int columnDirection) throws NotPossibleDirectionSettingException {
        if ((rowDirection<-1)||(rowDirection>1)||(columnDirection < -1)||(columnDirection > 1))
            throw new NotPossibleDirectionSettingException();
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public int getRowDirection() {
        return rowDirection;
    }

    public int getColumnDirection() {
        return columnDirection;
    }
}
