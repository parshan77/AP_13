package Model.Products;

import Model.Placement.Position;

public class Wool extends Product {
    private static int WOOL_VOLUME = 5;
    private static int WOOL_BUYCOST = 200;
    private static int WOOL_SELL_COST = 100;
    private static String name = "Wool";

    public Wool(Position position) {
        super(WOOL_VOLUME, WOOL_SELL_COST, WOOL_BUYCOST, name, position);
    }

    public Wool() {
        super(WOOL_VOLUME, WOOL_SELL_COST, WOOL_BUYCOST, name);
    }

    @Override
    public void show() {

    }

}
