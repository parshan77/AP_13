package Model.Products;

import Model.Placement.Position;

public class Dress extends Product {
    private static int volume = 8;
    private static int buyCost = 1000;
    private static int sellCost = 500;
    private static String name = "Dress";

    public Dress() {
        super(volume, sellCost, buyCost, name);
    }

    public Dress(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }

    @Override
    public void show() {

    }
}
