package Model.Products;

import Model.Placement.Position;

public class Cloth extends Product {
    private static int volume = 5;
    private static int buyCost = 500;
    private static int sellCost = 250;
    private static String name = "Cloth";

    public Cloth() {
        super(volume, sellCost, buyCost, name);
    }

    public Cloth(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }
}
