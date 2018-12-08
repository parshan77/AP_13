package Model.Workshops;

import Model.Products.ProcessedProduct;
import Model.Products.RawProduct;
import Model.User;

import java.util.ArrayList;

public abstract class Workshop<T extends RawProduct,V extends ProcessedProduct> {
    private int level = 1;
    protected String name;
    protected User user;

    public Workshop(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public int getLevel() {
        return level;
    }

    public abstract boolean upgrade();

    public abstract ArrayList<ProcessedProduct> process(ArrayList<RawProduct> rawStuff);
}
