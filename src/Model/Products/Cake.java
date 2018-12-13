package Model.Products;

public class Cake extends Product {
    public static int CAKE_VOLUME = 5;
    public static int CAKE_BUY_COST = 200;
    public static int CAKE_SELL_COST = 100;

    public Cake() {
        super(CAKE_VOLUME, CAKE_SELL_COST, CAKE_BUY_COST);
    }

    @Override
    public void show() {
        //TODO
    }
}
