package Model.Products;

import Model.Position;

public class Milk extends Product {
    public static int volume = 10;
    public static int buyCost = 2000;
    public static int sellCost = 1000;
    public static String name ="Milk";

    public Milk() {
    }

    public Milk(Position position) {
        super(position);
    }

    public void show() {
        //TODO
    }
}
