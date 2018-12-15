package Model.Workshops;

import Exceptions.WarehouseNoSuchStuffException;
import Exceptions.WorkshopNotEnoughResourcesException;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Model.Products.Product;
import Model.Warehouse;

import java.util.ArrayList;

public abstract class Workshop<T extends Product,V extends Product> implements VisibleInMap, Upgradable {
    private int level = 1;
    private String name;
    private Warehouse warehouse;

    public Workshop(Warehouse warehouse, String name) {
        this.warehouse = warehouse;
        this.name = name;
    }

    public void process() throws WorkshopNotEnoughResourcesException{
    }
}
