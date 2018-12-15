package Model.Products;

import Interfaces.Storable;
import Interfaces.Tradable;
import Interfaces.VisibleInMap;

public class Product implements Tradable, VisibleInMap, Storable {
    private int volume;
    private int sellCost;
    private int buyCost;
    private String name;

    public Product() {//todo: tu farzandash volume va sellcost va buycost bezar//
    }

    public String getName() {
        return name;
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

    @Override
    public void show() {
        //todo:bayad tu farzandash override beshe
    }
}
