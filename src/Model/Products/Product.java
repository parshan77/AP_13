package Model.Products;

import Interfaces.Storable;
import Interfaces.Tradable;
import Interfaces.VisibleInMap;
import Model.Position;

public abstract class Product implements Tradable, VisibleInMap, Storable {
    public static int volume;
    public static int sellCost;
    public static int buyCost;
    public static String name;
    private Position position;//todo :setter va getter mikhad

    public Product(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

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

}
