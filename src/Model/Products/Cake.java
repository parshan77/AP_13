package Model.Products;

import Model.Position;

public class Cake extends Product {
    public static int volume = 5;
    public static int buyCost = 200;
    public static int sellCost = 100;
    public static String name = "Cake";

    public Cake(Position position) {
        super(position);
    }

    public Cake() {
    }

    @Override
    public void show() {
        //TODO
    }

}
