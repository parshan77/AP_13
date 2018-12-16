package Model.Products;

import Model.Position;

public class Flour extends Product {
    public static int volume = 2;
    public static int buyCost = 20;
    public static int sellCost = 10;
    public static String name = "Flour";

    public Flour(Position position) {
        super(position);
    }

    public void show() {
        //TODO
    }
}
