package Model.Products;

import Interfaces.Storable;
import Interfaces.Tradable;
import Interfaces.VisibleInMap;
import Model.Placement.Position;

public abstract class Product implements Tradable, VisibleInMap, Storable {
    public static int volume;
    public static int sellCost;
    public static int buyCost;
    public static String name;
    private Position position;

    //todo:nemishe chanta costructor tarif kard
    public Product() {
    }

    public Product(Position position) {
        this.position = position;
    }


    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
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

}
