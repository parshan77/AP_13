package Model.Products;

import Model.Position;

public class Cookie extends Product {
    public static int volume = 3;
    public static int buyCost = 100;
    public static int sellCost = 50;
    public static String name = "Cookie";

    public Cookie(Position position) {
        super(position);
    }

    public void show() {
        //TODO
    }
}
