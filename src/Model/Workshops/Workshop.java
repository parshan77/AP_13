package Model.Workshops;

import Model.Products.Product;
import Model.Warehouse;

public abstract class Workshop<T extends Product,V extends Product> {
    private int level;

    public int getLevel() {
        return level;
    }

    private Warehouse warehouse;

    public Warehouse getWarehouse() {
        return warehouse;
    }

    private String name;

    public String getName() {
        return name;
    }

    public Workshop(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    private Product input;

    public Product getInput() {
        return input;
    }

    private Product output;

    public Product getOutput() {
        return output;
    }
    //TODO:bayad Requirement zade beshe.
    public  boolean upgrade(){

        /*!isMoneyEnought
                return false;
        money--;
        this.grade++;*/

    }

}
