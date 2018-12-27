package Model.Products;

import Interfaces.Storable;
import Interfaces.VisibleInMap;
import Model.Placement.Position;

public abstract class Product implements Storable, VisibleInMap {
    private int volume;
    private int sellCost;
    private int buyCost;
    private String name;
    private Position position;

    public Product(int volume, int sellCost, int buyCost, String name) {
        this.volume = volume;
        this.sellCost = sellCost;
        this.buyCost = buyCost;
        this.name = name;
    }

    public Product(int volume, int sellCost, int buyCost, String name, Position position) {
        this.volume = volume;
        this.sellCost = sellCost;
        this.buyCost = buyCost;
        this.name = name;
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
