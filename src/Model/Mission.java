package Model;

import Exceptions.NotEnoughMoneyException;
import Interfaces.Upgradable;
import Model.Vehicles.Helicopter;
import Model.Vehicles.Truck;
import Model.Workshops.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Mission {
    private long money = 0;
    private long timeNow = 0;
    private HashMap<String, Long> tasks = new HashMap<>();  //todo:hashcode ro benevis
    private int stars = 0;  //baraye shop va kharide kargah o ina be kar miad

    private Map map;
    private CakeBakery cakeBakery;
    private CookieBakery cookieBakery;
    private CostumeWorkshop costumeWorkshop;
    private EggPowderPlant eggPowderPlant;
    private SewingFactory sewingFactory;
    private Spinnery spinnery;
    private WeavingFactory weavingFactory;
    private Helicopter helicopter;
    private Truck truck;
    private Warehouse warehouse;
    private Shop shop;
    private Well well;

    public Workshop getWorkshop(String workshopName) {
        switch (workshopName.toLowerCase()) {
            case "cakebakery":
                return cakeBakery;
            case "cookiebakery":
                return cookieBakery;
            case "costumeworkshop":
                return costumeWorkshop;
            case "eggpowderplant":
                return eggPowderPlant;
            case "sewingfactory":
                return sewingFactory;
            case "spinnery":
                return spinnery;
            case "weavingfactory":
                return weavingFactory;
        }
        return null;
    }

    public Upgradable getUpgradableUnit(String unitName) {
        switch (unitName.toLowerCase()) {
            case "cakebakery":
                return cakeBakery;
            case "cookiebakery":
                return cookieBakery;
            case "costumeworkshop":
                return costumeWorkshop;
            case "eggpowderplant":
                return eggPowderPlant;
            case "sewingfactory":
                return sewingFactory;
            case "spinnery":
                return spinnery;
            case "weavingfactory":
                return weavingFactory;
            case "cat":
                //todo:upgrade kardane cat
            case "truck":
                return truck;
            case "helicopter":
                return helicopter;
            case "warehouse":
                return warehouse;
            case "well":
                return well;
        }
        return null;
    }

    public Map getMap() {
        return map;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }

    public Truck getTruck() {
        return truck;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Shop getShop() {
        return shop;
    }

    public Well getWell() {
        return well;
    }

    private boolean isMissionCompleted() {
        //todo
        return true;
    }

    public void addTask(String taskName, long finishingTurn) {
        tasks.put(taskName, finishingTurn);
    }

    public void passTime(int timeNow) {
        for (int i = 0; i < timeNow; i++) {
            clock();
        }
    }

    public ArrayList<String> clock() {
        timeNow++;
        ArrayList<String> finishedTasksNames = new ArrayList<>();
        for (String s : tasks.keySet()) {
            if (tasks.get(s) == timeNow) {
                finishedTasksNames.add(s);
            }
        }
        return finishedTasksNames;
        //todo:behtare har task ro ye object konim ya inke injuri ba string ha bazi konim?
    }

    public void addMoney(long amount) {
        money += amount;
    }

    public void spendMoney(long amount) throws NotEnoughMoneyException {
        if (amount > money)
            throw new NotEnoughMoneyException();
        money -= amount;
    }

    public long getMoney() {
        return money;
    }
}
