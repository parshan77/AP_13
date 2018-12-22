package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Direction;
import Model.Screen.Map;

public class Hen extends Domestic {
    public Hen(Map map) {
        super(map);
    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public void show() {

    }
}
