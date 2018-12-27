package Model.Products;

import Model.Placement.Position;

public class Egg extends Product {
    private static int volume = 1;
    private static int buyCost = 20;
    private static int sellCost = 10;
    private static String name ="Egg" ;

    public Egg(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }

    public Egg() {
        super(volume, sellCost, buyCost, name);
    }

    @Override
    public void show() {

    }

}
