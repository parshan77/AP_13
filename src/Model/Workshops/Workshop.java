package Model.Workshops;

import Exceptions.*;
import Interfaces.Upgradable;
import Interfaces.VisibleOutOfMap;
import Model.Mission;
import Model.Products.Product;
import Model.Warehouse;
import Utils.Utils;
import View.GamePlayView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Workshop implements Upgradable, VisibleOutOfMap {
    private int level = 1;
    protected String name;
    private String[] inputsNames;
    private String outputName;
    protected Mission mission;
    private int processTime;

    //constants
    private static int[] WORKSHOP_UPGRADE_COST = {150, 470, 1200};
    private static int WORKSHOP_MAX_LEVEL = 4;  // TODO: 1/27/2019 max level 5 e
    private int[] processTimesPerLevel;


    public Workshop(String name, String[] inputsNames, String outputName, Mission mission, int[] processTimes) {
        this.name = name;
        this.inputsNames = inputsNames;
        this.outputName = outputName;
        this.mission = mission;
        this.processTimesPerLevel = processTimes;
        processTime = processTimesPerLevel[0];
    }

    public boolean isFullyUpgraded() {
        return level == WORKSHOP_MAX_LEVEL;
    }

    public int getUpgradeCost() {
        if (level < WORKSHOP_MAX_LEVEL)
            return WORKSHOP_UPGRADE_COST[level - 1];
        else return 0;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
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
        if (mission == null)
            System.out.println("null mission");
    }

    public ArrayList<Product> start(ArrayList<Product> inputs) {
        // TODO: 1/31/2019 product haro ke tuye map nemizarim
        //        putProductsInMap(processedProducts);
        return processProducts(inputs);
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

    public ArrayList<Product> collectInputs() throws NotEnoughResourcesException {
        ArrayList<Product> inputs = new ArrayList<>(getOneCollectionFromWarehouse());
        //hatman bayad ye seri maade avalie dashte bashim -> age nadashte bashim khatte ghabl exception mide
        for (int i = 0; i < (level - 1); i++)
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
                } catch (CapacityExceededException | MissionCompletedException ignored) {
                }
            }
            throw new NotEnoughResourcesException();
        }
        return collection;
    }

    public void printInfo() {
        System.out.print("Inputs :");
        for (String inputsName : inputsNames) {
            System.out.print(inputsName + ", ");
        }
        System.out.println();
        System.out.println("Outputs :" + outputName);
        System.out.println("Level : " + level);
    }
}
