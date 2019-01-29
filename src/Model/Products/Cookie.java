package Model.Products;

import Model.Placement.Position;

public class Cookie extends Product {
    private static int volume = 3;
    private static int buyCost = 100;
    private static int sellCost = 50;
    private static String name = "Cookie";

    public Cookie() {
        super(volume, sellCost, buyCost, name);
    }

    public Cookie(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }
}
