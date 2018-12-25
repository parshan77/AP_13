package Model.Products;

import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Placement.Position;

public abstract class Product implements Storable, VisibleInMap {
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
    public int getBuyCost() {
        return buyCost;
    }

    @Override
    public int getSellCost() {
        return sellCost;
    }

    @Override
    public int getVolume() {
        return volume;
    }

}
