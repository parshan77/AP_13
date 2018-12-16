package Model.Workshops;

import Exceptions.*;
import Interfaces.Storable;
import Interfaces.Upgradable;
import Interfaces.VisibleInMap;
import Interfaces.VisibleOutOfMap;
import Model.Player;
import Model.Products.Product;
import Model.Warehouse;
import Utils.Utils;

import java.util.ArrayList;

public abstract class Workshop implements Upgradable, VisibleOutOfMap {
    protected int level = 0;
    protected String name;
    protected Warehouse warehouse;
    protected String[] inputsTypeName;
    protected String outputTypeName;
    protected Player player;

    //constants
    protected static int[] WORKSHOP_UPGRADE_COST = {150, 250, 350, 450};
    protected static int WORKSHOP_MAX_LEVEL = 3;

    public Workshop(String name, String[] inputsTypeName, String outputTypeName, Player player,Warehouse warehouse) {
        this.name = name;
        this.inputsTypeName = inputsTypeName;
        this.outputTypeName = outputTypeName;
        this.player = player;
        this.warehouse = warehouse;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, WorkshopMaxLevelExceeded {
        if (level == WORKSHOP_MAX_LEVEL) throw new WorkshopMaxLevelExceeded();
        player.spendMoney(WORKSHOP_UPGRADE_COST[level]);
        level++;
    }

    public ArrayList<Product> start() throws WorkshopNotEnoughResourcesException {
        ArrayList<Product> inputs = collectInputs();
        return produceProducts(inputs);
    }

    private ArrayList<Product> produceProducts(ArrayList<Product> inputs) {
        int numberOfOutputs = inputs.size() / inputsTypeName.length;
        ArrayList<Product> outputs = new ArrayList<>();
        for (int i = 0; i < numberOfOutputs; i++) {
            try {
                outputs.add(Utils.getProductObject(outputTypeName));
            } catch (ProductNameNotFoundException e) {}
        }
        return outputs;
    }

    private ArrayList<Product> collectInputs() throws WorkshopNotEnoughResourcesException {
        ArrayList<Product> inputs = new ArrayList<>();
        inputs.addAll(getOneCollectionFromWarehouse());
        for (int i = 0; i < (level - 2); i++) {
            try {
                inputs.addAll(getOneCollectionFromWarehouse());
            } catch (WorkshopNotEnoughResourcesException e1) {
                break;
            }
        }
        return inputs;
    }

    private ArrayList<Product> getOneCollectionFromWarehouse() throws WorkshopNotEnoughResourcesException {
        ArrayList<Product> collection = new ArrayList<>();
        try {
            for (String s : inputsTypeName) {
                collection.add((Product) warehouse.get(s));
            }
        } catch (WarehouseNoSuchStuffException e) {
            for (Product product : collection) {
                try {
                    warehouse.store(product);
                } catch (WarehouseNotEnoughCapacityException e1) {
                    //exception emkan nadare rokh bede
                }
                throw new WorkshopNotEnoughResourcesException();
            }
        }
        return collection;
    }
}
