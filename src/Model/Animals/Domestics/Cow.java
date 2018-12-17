package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Direction;
import Model.Map;

public class Cow extends Domestic {
    public Cow(Map map) {
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
