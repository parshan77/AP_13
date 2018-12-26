package Model.Animals.Domestics;

import Model.Animals.Domestic;
import Model.Placement.Direction;
import Model.Placement.Position;
import Model.Products.Milk;
import Model.Screen.Map;

public class Cow extends Domestic {
    public Cow(Map map, Direction direction, Position position) {
        super(map, direction, position);
    }


    public void makeProduct() {
        super.makeProduct("milk");
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void show() { }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void step() {
        super.step();
    }

    @Override
    public void smartStep() {
        super.smartStep();
    }

    @Override
    public void makeHungry() {
        super.makeHungry();
    }

    @Override
    public void eat() {
        super.eat();
    }
}
