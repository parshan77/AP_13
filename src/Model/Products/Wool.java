package Model.Products;

public class Wool extends Product {
    public static int WOOL_VOLUME = 5;
    public static int WOOL_BUY_COST = 200;
    public static int WOOL_SELL_COST = 100;

    public Wool() {
        super(WOOL_VOLUME, WOOL_SELL_COST, WOOL_BUY_COST);
    }

    @Override
    public void show() {
        //TODO
    }
}
