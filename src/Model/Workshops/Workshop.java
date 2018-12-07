package Model.Workshops;

import Model.Products.ProcessedProduct;
import Model.Products.RawProduct;

import java.util.ArrayList;

public abstract class Workshop {
    private int level;
    private int activationPrice;
    private RawProduct input;
    private ProcessedProduct output;

    public abstract boolean upgrade();

    public abstract ArrayList<ProcessedProduct> process(ArrayList<RawProduct> rawStuff);
}
