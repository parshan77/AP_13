package Model.Workshops;

import Model.Products.ProcessedProduct;
import Model.Products.RawProduct;
import Model.User;
import Model.Warehouse;

import java.util.ArrayList;

public abstract class Workshop<T extends RawProduct,V extends ProcessedProduct> {
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

    private RawProduct input;

    public RawProduct getInput() {
        return input;
    }

    private ProcessedProduct output;

    public ProcessedProduct getOutput() {
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
