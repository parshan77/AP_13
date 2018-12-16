package Model.Workshops;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Model.Player;
import Model.Products.Product;
import Model.Warehouse;

import java.util.ArrayList;

public abstract class Workshop implements Upgradable {
    protected int level = 0;
    protected String name;
    protected Warehouse warehouse;
    protected String[] inputsTypeName;
    protected String outputTypeName;
    protected Player player;

    //constants
    protected static int[] WORKSHOP_UPGRADE_COST = {150, 250, 350, 450};
    protected static int WORKSHOP_MAX_LEVEL = 4;

    @Override
    public void upgrade() throws NotEnoughMoneyException, WorkshopMaxLevelExceeded {
        if (level == WORKSHOP_MAX_LEVEL) throw new WorkshopMaxLevelExceeded();
        player.spendMoney(WORKSHOP_UPGRADE_COST[level]);
        level ++;
    }

    public ArrayList<Product> start() {
        return null;
       /* ArrayList<Product> rawProducts = new ArrayList<>();
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
        return processedProducts;*/
    }
}
