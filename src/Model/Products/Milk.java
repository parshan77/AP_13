package Model.Products;

public class Milk extends Product {
    public static int MILK_VOLUME = 10;
    public static int MILK_BUY_COST = 2000;
    public static int MILK_SELL_COST = 1000;

    public Milk() {
        super(MILK_VOLUME, MILK_SELL_COST, MILK_BUY_COST);
    }

    @Override
    public void show() {
        //TODO
    }
}
