package Model.Workshops;

import Model.Products.ProcessedProduct;
import Model.Products.RawProduct;
import Model.User;

import java.util.ArrayList;

public abstract class Workshop<T extends RawProduct,V extends ProcessedProduct> {
    private int level;
    private User user;
    private String name;

    public Workshop(User user, String name) {
        this.user = user;
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
