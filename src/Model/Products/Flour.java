package Model.Products;

import Model.Placement.Position;

public class Flour extends Product {
    private static int FLOUR_VOLUME = 2;
    private static int FLOUR_BUY_COST= 20;
    private static int FLOUR_SELL_COST = 10;
    private static String name = "Flour";

    public Flour(Position position) {
        super(FLOUR_VOLUME,FLOUR_SELL_COST,FLOUR_BUY_COST,name,position);
    }

    public Flour() {
        super(FLOUR_VOLUME,FLOUR_SELL_COST,FLOUR_BUY_COST,name);
    }

    @Override
    public void show() {

    }

}
