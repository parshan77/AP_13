package Model.Products;

import Model.Placement.Position;

public class EggPowder extends Product{
    private static int volume = 2;
    private static int buyCost = 100;
    private static int sellCost = 50;
    private static String name = "EggPowder";

    public EggPowder(Position position) {
        super(volume, sellCost, buyCost, name, position);
    }

    public EggPowder() {
        super(volume, sellCost, buyCost, name);
    }
}
