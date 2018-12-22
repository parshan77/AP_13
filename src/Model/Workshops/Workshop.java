package Model.Workshops;

import Exceptions.*;
import Exceptions.WorkshopNotEnoughResourcesException;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;
import Model.Mission;
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
    protected Mission mission;

    //constants
    protected static int[] WORKSHOP_UPGRADE_COST = {150, 250, 350, 450};
    protected static int WORKSHOP_MAX_LEVEL = 3;

    public Workshop(String name, String[] inputsTypeName, String outputTypeName, Mission mission, Warehouse warehouse) {
        this.name = name;
        this.inputsTypeName = inputsTypeName;
        this.outputTypeName = outputTypeName;
        this.mission = mission;
        this.warehouse = warehouse;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == WORKSHOP_MAX_LEVEL) throw new MaxLevelExceededException();
        mission.spendMoney(WORKSHOP_UPGRADE_COST[level]);
        level++;
    }

    public ArrayList<Product> start() throws WorkshopNotEnoughResourcesException {
        ArrayList<Product> inputs = collectInputs();
        return produceProducts(inputs);
        //todo: object hayi ke tolid kardaro bayad bendaze tu map
    }

    private ArrayList<Product> produceProducts(ArrayList<Product> inputs) {
        int numberOfOutputs = inputs.size() / inputsTypeName.length;
        ArrayList<Product> outputs = new ArrayList<>();
        for (int i = 0; i < numberOfOutputs; i++) {
            try {
                outputs.add(Utils.getProductObject(outputTypeName));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
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
        } catch (NotFoundException e) {
            for (Product product : collection) {
                try {
                    warehouse.store(product);
                } catch (CapacityExceededException e1) {
                    //exception emkan nadare rokh bede
                    e1.printStackTrace();
                }
            }
            throw new WorkshopNotEnoughResourcesException();
        }
        return collection;
    }
}
