package Model.Products;

import Model.Position;

public class Egg extends Product {
    public static int volume = 1;
    public static int buyCost = 20;
    public static int sellCost = 10;
    public static String name ="Egg" ;

    public Egg(Position position) {
        super(position);
    }

    public Egg() {
    }

    @Override
    public void show() {
        //todo
    }
}
