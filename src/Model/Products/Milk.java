package Model.Products;

import Model.Placement.Position;

public class Milk extends Product {
    private static int MILK_VOLUME = 10;
    private static int MILK_BUY_COST = 250;
    private static int MILK_SELL_COST = 150;
    private static String name = "Milk";

    public Milk() {
        super(MILK_VOLUME, MILK_SELL_COST, MILK_BUY_COST, name);
    }

    public Milk(Position position) {
        super(MILK_VOLUME, MILK_SELL_COST, MILK_BUY_COST, name, position);
    }

    @Override
    public void show() {

    }
}
