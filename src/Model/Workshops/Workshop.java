package Model.Workshops;

import Model.Products.ProcessedProduct;
import Model.Products.RawProduct;
import Model.User;
import Model.Warehouse;

import java.util.ArrayList;

public abstract class Workshop<T extends RawProduct,V extends ProcessedProduct> {
    private int level;
    private Warehouse warehouse;
    private String name;

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

    public abstract boolean upgrade();

}
