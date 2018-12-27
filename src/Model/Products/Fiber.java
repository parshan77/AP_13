package Model.Products;

import Model.Placement.Position;

public class Fiber extends Product {
    private static int volume = 2;
    private static int buyCost = 50;
    private static int sellCost = 25;
    private static String name = "Fiber";

    public Fiber(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }

    public Fiber() {
        super(volume, sellCost, buyCost, name);
    }

    @Override
    public void show() {

    }

}
