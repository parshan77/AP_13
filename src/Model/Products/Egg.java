package Model.Products;

public class Egg extends Product {
    public static int EGG_VOLUME = 1;
    public static int EGG_BUY_COST = 20;
    public static int EGG_SELL_COST = 10;

    public Egg() {
        super(EGG_VOLUME, EGG_SELL_COST, EGG_BUY_COST);
    }

    @Override
    public void show() {
        //todo
    }
}
