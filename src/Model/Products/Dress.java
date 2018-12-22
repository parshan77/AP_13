package Model.Products;

import Model.Position;

public class Dress extends Product {
    public static int volume = 8;
    public static int buyCost = 1000;
    public static int sellCost = 500;
    public static String name = "Dress";

    public Dress() {
    }

    public Dress(Position position) {
        super(position);
    }

    public void show(){
        //todo
    }
}
