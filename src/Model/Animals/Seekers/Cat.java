package Model.Animals.Seekers;

import Exceptions.MaxLevelExceededException;
import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Model.Animals.Seeker;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Screen.Map;

public class Cat extends Seeker implements Upgradable {
    private static int level = 0;
    private Map map;
    public Cat(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }

    @Override
    public void move() {
        if (level == 0)
            for (int i = 0; i < pace; i++) {
                step();
            }
        else
            for (int i = 0; i < pace; i++){
                smartStep();
            }
    }

    public void step(){
        super.step();
    }

    public void smartStep(){
        //todo: getClosesetObject maikhaim
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == 0)
            level++;
    }

    @Override
    public void show() {

    }
}
