package Model.Workshops;

import Exceptions.*;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;
import Model.Mission;
import Model.Products.Product;
import Model.Warehouse;
import Utils.Utils;

import java.util.ArrayList;

public abstract class Workshop implements Upgradable, VisibleOutOfMap {
    private int level = 1;
    protected String name;
    private String[] inputsNames;
    private String outputName;
    protected Mission mission;
    private int processTime;

    //constants
    private static int[] WORKSHOP_UPGRADE_COST = {150, 300, 600};
    private static int WORKSHOP_MAX_LEVEL = 4;
    private int[] processTimesPerLevel;

    public Workshop(String name, String[] inputsNames, String outputName, Mission mission,int[] processTimes) {
        this.name = name;
        this.inputsNames = inputsNames;
        this.outputName = outputName;
        this.mission = mission;
        this.processTimesPerLevel = processTimes;
        processTime = processTimesPerLevel[0];
    }

    public int getProcessTime() {
        return processTime;
    }

    @Override
    public void upgrade() throws NotEnoughMoneyException, MaxLevelExceededException {
        if (level == WORKSHOP_MAX_LEVEL) throw new MaxLevelExceededException();
        mission.spendMoney(WORKSHOP_UPGRADE_COST[level - 1]);
        level++;
        processTime = processTimesPerLevel[level - 1];
    }

    public ArrayList<Product> prepareForStarting() throws NotEnoughResourcesException {
        ArrayList<Product> inputs = collectInputs();
        return inputs;
    }

    public void start(ArrayList<Product> inputs) {
        ArrayList<Product> processedProducts = processProducts(inputs);
        putProductsInMap(processedProducts);
    }

    protected abstract void putProductsInMap(ArrayList<Product> processedProducts);

    private ArrayList<Product> processProducts(ArrayList<Product> inputs) {
        int numberOfOutputs = inputs.size() / inputsNames.length;
        ArrayList<Product> outputs = new ArrayList<>();
        for (int i = 0; i < numberOfOutputs; i++) {
            try {
                outputs.add(Utils.getProductObject(outputName));
            } catch (NotFoundException e) {
                e.printStackTrace();
                //bayad hatman in tedad sakhte beshe -> ignored
            }
        }
        return outputs;
    }

    private ArrayList<Product> collectInputs() throws NotEnoughResourcesException {
        ArrayList<Product> inputs = new ArrayList<>(getOneCollectionFromWarehouse());
        //hatman bayad ye seri maade avalie dashte bashim -> age nadashte bashim khatte ghabl exception mide
        for (int i = 0; i < (level - 2); i++)
            try {
                inputs.addAll(getOneCollectionFromWarehouse());
            } catch (NotEnoughResourcesException e1) {
                break;
            }
        return inputs;
    }

    private ArrayList<Product> getOneCollectionFromWarehouse() throws NotEnoughResourcesException {
        ArrayList<Product> collection = new ArrayList<>();
        Warehouse warehouse = mission.getWarehouse();

        try {
            for (String s : inputsNames) {
                collection.add((Product) warehouse.getAndDiscard(s));
            }
        } catch (NotFoundException e) {
            for (Product product : collection) {
                try {
                    warehouse.store(product);
                } catch (CapacityExceededException | LevelFinishedException ignored) {
                }
            }
            throw new NotEnoughResourcesException();
        }
        return collection;
    }
}
