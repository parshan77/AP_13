package Model.Products;

import Model.Placement.Position;

public class Cloth extends Product {
    public static int volume = 5;
    public static int buyCost = 500;
    public static int sellCost = 250;
    public static String name = "EggPowder";

    public Cloth() {
    }

    public Cloth(Position position) {
        super(position);
    }

    public void show(){
        //todo:
    }
}
