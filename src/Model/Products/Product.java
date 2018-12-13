package Model.Products;

import Interfaces.Storable;
import Interfaces.Tradable;
import Interfaces.Visible;

public abstract class Product implements Tradable, Visible , Storable {
    private int volume;
    private int sellCost;
    private int buyCost;

    public Product(int volume, int sellCost, int buyCost) {
        this.volume = volume;
        this.sellCost = sellCost;
        this.buyCost = buyCost;
    }

    @Override
    public long getBuyCost() {
        return buyCost;
    }

    @Override
    public long getSellCost() {
        return sellCost;
    }

    @Override
    public int getVolume() {
        return volume;
    }
}
