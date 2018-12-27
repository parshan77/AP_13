package Model.Products;

import Model.Placement.Position;

public class Cake extends Product {
    private static int volume = 5;
    private static int buyCost = 200;
    private static int sellCost = 100;
    private static String name = "Cake";

    public Cake(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }

    public Cake() {
        super(volume, sellCost, buyCost, name);
    }

    @Override
    public void show() {

    }

}
