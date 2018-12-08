package Model.Workshops;

import Model.Products.ProcessedProduct;
import Model.Products.RawProduct;

import java.util.ArrayList;

public abstract class Workshop {
    private int level;

    public int getLevel() {
        return level;
    }

    private int activationPrice;

    public int getActivationPrice() {
        return activationPrice;
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
