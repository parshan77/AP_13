package Model;

import Exceptions.FullWarehouseException;
import Exceptions.WarehouseNoSuchStuffException;
import Interfaces.Storable;
import Interfaces.Visible;

import java.util.ArrayList;

public class Warehouse implements Visible {
    private int capacity;
    private int occupiedCapacity;
    private ArrayList<Object> items = new ArrayList<>();

    public Warehouse(int capacity) {
        //TODO:capacity moghe e shuru cheghadre?
        this.capacity = capacity;
        occupiedCapacity = 0;
    }

    @Override
    public void show() {
    }

    public void store(Storable object) throws FullWarehouseException {
        int objectVolume = object.getVolume();
        if (capacity - occupiedCapacity < objectVolume)
            throw new FullWarehouseException();
        occupiedCapacity += objectVolume;
        items.add(object);
    }

    //todo:in bayad ba generic zade beshe
    public Storable get(Storable object) throws WarehouseNoSuchStuffException {
        if (!items.contains(object)) {
            throw new WarehouseNoSuchStuffException();
        }
        occupiedCapacity += object.getVolume();
        items.remove(object);
        return object;
    }
}
