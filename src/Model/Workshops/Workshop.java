package Model.Workshops;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Model.Player;
import Model.Products.Product;
import Model.Warehouse;

import java.util.ArrayList;

public abstract class Workshop<T extends Product, V extends Product> implements Upgradable {
    protected int level = 0;
    protected String name;
    protected Warehouse warehouse;
    protected Class<T> inputClazz;
    protected Class<V> outputClazz;
    protected Player player;
    //constants
    protected static int[] WORKSHOP_UPGRADE_COST = {150, 250, 350, 450};
    protected static int WORKSHOP_MAX_LEVEL = 4;

    public Workshop(Warehouse warehouse, Class<T> inputClazz, Class<V> outputClazz, Player player) {
        this.warehouse = warehouse;
        this.inputClazz = inputClazz;
        this.outputClazz = outputClazz;
        this.player = player;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, WorkshopMaxLevelExceeded {
        if (level == WORKSHOP_MAX_LEVEL) throw new WorkshopMaxLevelExceeded();
        player.spendMoney(WORKSHOP_UPGRADE_COST[level]);
        level ++;
    }

    private V getOutputInstance() throws IllegalAccessException, InstantiationException {
        return outputClazz.newInstance();
    }

    private boolean checkInputType(Object Object) {
        return inputClazz.isInstance(Object);
    }

    private T getInputInstance() throws IllegalAccessException, InstantiationException {
        return inputClazz.newInstance();
    }

    public ArrayList<Product> start()
            throws WorkshopNotEnoughResourcesException, InstantiationException, IllegalAccessException {
        ArrayList<Product> rawProducts = new ArrayList<>();
        Product inputSample = getInputInstance();

        try {
            Product product = (Product) warehouse.get(inputSample);
            rawProducts.add(product);
        } catch (WarehouseNoSuchStuffException e) {
            throw new WorkshopNotEnoughResourcesException();
        }

        for (int i = 0; i < (level - 2); i++) {
            try {
                Product product = (Product) warehouse.get(inputSample);
                rawProducts.add(product);
            } catch (WarehouseNoSuchStuffException e) {
                break;
            }
        }

        //making processed products
        ArrayList<Product> processedProducts = new ArrayList<>();
        for (int i = 0; i < rawProducts.size(); i++) {
            processedProducts.add(getOutputInstance());
        }
        return processedProducts;
    }
}
